package de.vapecloud.driver.networking.netty.handler;

import de.vapecloud.driver.networking.netty.packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerInboundHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
    }
}
