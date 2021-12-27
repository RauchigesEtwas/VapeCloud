package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETServer;
import de.vapecloud.vapenet.channel.ChannelHandler;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.protocol.Packet;
import lombok.SneakyThrows;

import java.io.IOException;

public class Server {


    private VapeNETServer server;
    private int port;

    public Server() {

    }


    public VapeNETServer getServer() {
        return server;
    }

    public Server bind(int port){
        this.port = port;
        return this;
    }


    @SneakyThrows
    public void create(){
        this.server = new VapeNETServer();
        this.server.init(channel -> {
            ChannelPipeline pipeline = channel.getPipeline();
            pipeline.handle(new ChannelHandler() {
                @Override
                public void handlePacket(IChannel channel, Packet packet) throws IOException {
                    server.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handlePacket(channel, packet);
                    });
                }

                @Override
                public void handleConnected(IChannel channel) throws IOException {
                    server.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleConnected(channel);
                    });
                }

                @Override
                public void handleDisconnected(IChannel channel) throws IOException {
                    server.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleDisconnected(channel);
                    });
                }

                @Override
                public void handleException(Exception exception) {
                    server.getPacketManager().getAllListeners().forEach(handler -> {
                        handler.handleException(exception);
                    });
                }
            });
        }).bind(this.port);
    }
}
