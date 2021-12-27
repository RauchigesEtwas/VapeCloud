package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;


public class VapeNETWorker implements Runnable, IVapeNETStructure{
    private final Thread thread = new Thread(this);
    private ServerSocket socket;

    private boolean connected;

    private final List<IChannel> channels = new ArrayList<>();

    private final ChannelPipeline pipeline = new ChannelPipeline();

    private IChannelInitializer initializer;

    public VapeNETWorker() {
        try {
            this.socket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(InetSocketAddress address) throws IOException {
        socket.bind(address);
        if (getOption(VapeNETOption.TIMEOUT) > 0) socket.setSoTimeout(getOption(VapeNETOption.TIMEOUT));
        connected = true;
        this.thread.start();
    }

    @Override
    public void run() {
        while (connected) {
            try {
                VapeNETChannel channel = new VapeNETChannel(this, socket.accept());
                channels.add(channel);
                if (initializer != null) initializer.initChannel(channel);
                channel.getPipeline().getHandler().handleConnected(channel);
                channel.start();
            } catch (IOException e) {
                pipeline.getHandler().handleException(e);
                close();
            }
        }
    }

    @Override
    public void close() {
        try {
            connected = false;
            for (int i = channels.size() - 1; i >= 0; i--) channels.get(i).close();
            socket.close();
        } catch (IOException e) {
            pipeline.getHandler().handleException(e);
        }
    }

    @Override
    public void sendPacket(Packet packet) {
        for (int i = channels.size() - 1; i >= 0; i--) channels.get(i).sendPacket(packet);
    }

    @Override
    public VapeNETWorker init(IChannelInitializer initializer) {
        this.initializer = initializer;
        return this;
    }

    @Override
    public VapeNETWorker option(VapeNETOption<?> option, Object value) {
        option.setValue(value);
        return this;
    }

    @Override
    public <T> T getOption(VapeNETOption<T> option) {
        return option.value;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    public InetAddress getInetAddress() {
        return socket.getInetAddress();
    }

    public SocketAddress getLocalSocketAddress() {
        return socket.getLocalSocketAddress();
    }

    public int getPort() {
        return socket.getLocalPort();
    }

    public List<IChannel> getChannels() {
        return channels;
    }
}
