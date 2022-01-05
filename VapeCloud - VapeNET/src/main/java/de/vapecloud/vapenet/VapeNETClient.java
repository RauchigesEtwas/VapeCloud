package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.PacketManager;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class VapeNETClient implements IVapeNETStructure{

    private final VapeNETChannel channel = new VapeNETChannel(this);

    private String host;
    private Integer port;

    public VapeNETClient() {
    }



    public VapeNETClient bind(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    public void connect() throws IOException {
        channel.connect(new InetSocketAddress(host,port));


        channel.start();

    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public void sendPacket(Packet packet) {
        channel.sendPacket(packet);
    }

    @Override
    public VapeNETClient init(IChannelInitializer initializer) {
        channel.init(initializer);
        return this;
    }

    @Override
    public VapeNETClient option(VapeNETOption<?> option, Object value) {
        option.setValue(value);
        return this;
    }

    @Override
    public <T> T getOption(VapeNETOption<T> option) {
        return option.value;
    }

    @Override
    public boolean isConnected() {
        return channel.isConnected();
    }

    public IChannel getChannel() {
        return channel;
    }
}
