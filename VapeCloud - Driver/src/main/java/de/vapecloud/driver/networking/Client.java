package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETClient;
import de.vapecloud.vapenet.channel.ChannelHandler;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.protocol.Packet;
import lombok.SneakyThrows;

import java.io.IOException;

public class Client {

    private VapeNETClient client;
    private int port;
    private String host;

    public Client() {}


    public Client bind(String host, int port){
        this.host = host;
        this.port = port;
        return this;
    }

    public VapeNETClient getClient() {
        return client;
    }

    @SneakyThrows
    public void create(){

        this.client = new VapeNETClient();
        client.init(channel -> {
            ChannelPipeline pipeline = channel.getPipeline();
            pipeline.handle(new ChannelHandler() {
                @Override
                public void handlePacket(IChannel channel, Packet packet) throws IOException {
                    client.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handlePacket(channel, packet);
                    });
                }

                @Override
                public void handleConnected(IChannel channel) throws IOException {
                    client.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleConnected(channel);
                    });
                }

                @Override
                public void handleDisconnected(IChannel channel) throws IOException {
                    client.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleDisconnected(channel);
                    });
                }

                @Override
                public void handleException(Exception exception) {
                    client.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleException(exception);
                    });
                }
            });
        }).bind(this.host, this.port).connect();
    }
}
