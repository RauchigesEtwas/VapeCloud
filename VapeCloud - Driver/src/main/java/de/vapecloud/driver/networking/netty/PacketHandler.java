package de.vapecloud.driver.networking.netty;

import de.vapecloud.driver.networking.netty.packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

public class PacketHandler extends SimpleChannelInboundHandler<Packet> {


    private boolean isService;

    public PacketHandler(boolean isService) {
        this.isService = isService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {}
}
