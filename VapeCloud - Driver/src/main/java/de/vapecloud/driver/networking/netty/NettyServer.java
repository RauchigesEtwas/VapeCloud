package de.vapecloud.driver.networking.netty;

import de.vapecloud.driver.networking.netty.coder.PacketDecoder;
import de.vapecloud.driver.networking.netty.coder.PacketEncoder;
import de.vapecloud.driver.networking.netty.handler.ServerInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

/*
 * Created AT: 20.12.2021
 * Created by Robin B. (RauchigesEtwas)
 */

public class NettyServer extends Thread{

    private int port;
    public NettyServer(int port) {
        this.port = port;
        this.start();
    }

    @Override
    public void run() {
        EventLoopGroup eventLoopGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            Channel channel = new ServerBootstrap().group(eventLoopGroup).option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE).channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class).childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new PacketEncoder());
                    pipeline.addLast(new PacketDecoder());
                    pipeline.addLast(new ServerInboundHandler());
                    ///Driver.getInstance().getClients().put("Cloud", channel);
                    //TODO: Channel sort to Client
                }
            }).bind(new InetSocketAddress("0.0.0.0", port)).sync().channel();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
