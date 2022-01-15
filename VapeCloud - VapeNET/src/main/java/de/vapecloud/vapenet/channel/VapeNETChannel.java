package de.vapecloud.vapenet.channel;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:38
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.*;
import de.vapecloud.vapenet.handlers.listener.ClientConnectEvent;
import de.vapecloud.vapenet.handlers.listener.ClientDisconectEvent;
import de.vapecloud.vapenet.handlers.listener.NetworkExceptionEvent;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;
import de.vapecloud.vapenet.protocol.PacketBuffer;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class VapeNETChannel implements IChannel, Runnable {

    private final Thread thread = new Thread(this);

    private final ChannelPipeline pipeline = new ChannelPipeline();

    private IChannelInitializer initializer;

    private final IVapeNETStructure structure;

    private final Socket socket;

    private boolean connected;


    public Socket getSocket() {
        return socket;
    }

    public VapeNETChannel(IVapeNETStructure structure, Socket socket) {
        this.structure = structure;
        this.socket = socket;
        this.connected = true;
    }

    public VapeNETChannel(IVapeNETStructure structure) {
        this.structure = structure;
        this.socket = new Socket();
    }

    public void connect(SocketAddress address) {
        try {
            socket.connect(address);
            connected = true;
            initializer.initChannel(this);
            VapeNetBootStrap.packetListenerHandler.executeEvent(new ClientConnectEvent(this));
        } catch (IOException e) {

        }

    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        while (connected) {
            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                int read, count = 0;
                while (connected && (read = inputStream.readByte()) > -1) {
                    count++;
                    if (structure.getOption(VapeNETOption.BUFFER_SIZE) < count) {
                        throw new Exception("The buffer is too big, allowed size " + structure.getOption(VapeNETOption.BUFFER_SIZE));
                    }
                    array.write(read);
                }
                Packet decode = pipeline.getDecoder().decode(new Packet() {
                    @Override
                    public void write(IPacketBuffer buffer) {}
                    @Override
                    public void read(IPacketBuffer buffer) {}
                }, new PacketBuffer(array.toByteArray()));
                VapeNetBootStrap.packetListenerHandler.executeEvent(new PacketReceivedEvent(this, decode));
            } catch (IOException e) {
                VapeNetBootStrap.packetListenerHandler.executeEvent(new NetworkExceptionEvent(e));
                close();
            } catch (Exception exception) {
                VapeNetBootStrap.packetListenerHandler.executeEvent(new NetworkExceptionEvent(exception));
                close();
            }
        }
    }

    @Override
    public void close() {
        try {
            if (socket != null && connected) {
                connected = false;
                socket.close();
                if (structure instanceof VapeNETServer) ((VapeNETServer) structure).getChannels().remove(this);
                VapeNetBootStrap.packetListenerHandler.executeEvent(new ClientDisconectEvent(this));

            }
        } catch (IOException e) {
            VapeNetBootStrap.packetListenerHandler.executeEvent(new NetworkExceptionEvent(e));
            close();
        }
    }

    @Override
    public void sendPacket(Packet packet) {
        try {
            DataOutputStream data = new DataOutputStream(socket.getOutputStream());
            pipeline.getEncoder().encode(data, packet, new PacketBuffer());
        } catch (IOException e) {
            VapeNetBootStrap.packetListenerHandler.executeEvent(new NetworkExceptionEvent(e));
        }
    }

    public void init(IChannelInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public ChannelPipeline getPipeline() {
        return pipeline;
    }

    @Override
    public SocketAddress getRemoteAddress() {
        return socket.getRemoteSocketAddress();
    }

    @Override
    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }
}
