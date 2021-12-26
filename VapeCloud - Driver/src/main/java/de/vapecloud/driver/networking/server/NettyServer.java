package de.vapecloud.driver.networking.server;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.networking.base.coder.PacketDecoder;
import de.vapecloud.driver.networking.base.coder.PacketEncoder;
import de.vapecloud.driver.networking.packets.client.to.AuthRequestPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;
import java.util.UUID;


/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */
public class NettyServer extends Thread{

    private int port;
    public NettyServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        /**
         * create an new NettyServer Syncron Connection
         */

        EventLoopGroup eventLoopGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            Channel channel = new ServerBootstrap().group(eventLoopGroup).option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE).channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class).childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new PacketEncoder());
                    pipeline.addLast(new PacketDecoder());
                    pipeline.addLast(new ServerInboundHandler());

                    String key = UUID.randomUUID().toString() + UUID.randomUUID().toString();

                    VapeDriver.networkManager.getClientManager().createChannel(key, channel);
                }
            }).bind(new InetSocketAddress("0.0.0.0", port)).sync().channel();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}