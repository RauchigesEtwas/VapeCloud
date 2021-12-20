package de.vapecloud.driver.networking.netty.coder;

import de.vapecloud.driver.networking.netty.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        /*int packetId = Driver.getInstance().getPacketManager().getIdFromPacket(packet);
        byteBuf.writeInt(packetId);*/
        byteBuf.writeInt(packet.getId());
        packet.encodePayload(byteBuf);
    }
}
