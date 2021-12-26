package de.vapecloud.driver.networking;

import de.vapecloud.driver.networking.base.packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    public PacketHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext channels, Packet packet){}

    @Override
    public void exceptionCaught(ChannelHandlerContext channels, Throwable cause) {}
}
