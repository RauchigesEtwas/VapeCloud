package de.vapecloud.driver.networking.netty;


import de.vapecloud.driver.networking.netty.coder.PacketDecoder;
import de.vapecloud.driver.networking.netty.coder.PacketEncoder;
import de.vapecloud.driver.networking.netty.handler.ClientInboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

public class NettyClient extends Thread {

    private String ip;
    private int port;
    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {

        /**
         * create an new NettyClient Syncron Connection to running NettyServer
         */

        EventLoopGroup eventLoopGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            Channel channel = new Bootstrap().group(eventLoopGroup).option(ChannelOption.SO_SNDBUF, Integer.MAX_VALUE).option(ChannelOption.SO_RCVBUF, Integer.MAX_VALUE).channel(Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new PacketEncoder());
                    pipeline.addLast(new PacketDecoder());
                    pipeline.addLast(new ClientInboundHandler());
                }
            }).connect(ip, port).sync().channel();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
