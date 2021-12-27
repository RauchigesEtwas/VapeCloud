package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:35
 * Created by Robin B. (RauchigesEtwas)
 */
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.handlers.PacketManager;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

public class VapeNETServer implements IVapeNETStructure {


    private final VapeNETWorker worker;
    private PacketManager packetManager;

    public VapeNETServer() {
        this.worker = new VapeNETWorker();
        this.packetManager = new PacketManager();
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }

    public void bind(int port) throws IOException {
        worker.bind(new InetSocketAddress(port));
    }

    @Override
    public void close() {
        worker.close();
    }

    @Override
    public void sendPacket(Packet packet) {
        worker.sendPacket(packet);
    }

    @Override
    public VapeNETServer init(IChannelInitializer initializer) {
        worker.init(initializer);
        return this;
    }

    @Override
    public VapeNETServer option(VapeNETOption<?> option, Object value) {
        worker.option(option, value);
        return this;
    }

    @Override
    public <T> T getOption(VapeNETOption<T> option) {
        return worker.getOption(option);
    }

    @Override
    public boolean isConnected() {
        return worker.isConnected();
    }

    public InetAddress getInetAddress() {
        return worker.getInetAddress();
    }

    public SocketAddress getLocalSocketAddress() {
        return worker.getLocalSocketAddress();
    }

    public int getPort() {
        return worker.getPort();
    }

    public List<IChannel> getChannels() {
        return worker.getChannels();
    }
}
