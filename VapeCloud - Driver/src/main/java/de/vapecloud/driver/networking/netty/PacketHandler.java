package de.vapecloud.driver.networking.netty;

import de.vapecloud.driver.networking.netty.packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    /*private Channel channel;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
    }*/
    private boolean isService;

    public PacketHandler(boolean isService) {
        this.isService = isService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //Driver.getInstance().getLogger().send("LOL", LoggerType.INFO);
        if(isService) {
        } else {

        }
        //Driver.getInstance().getLogger().send("Remote Address: " + ctx.channel().remoteAddress().toString().replace("/", "").split(":")[0], LoggerType.INFO);
    }
}
