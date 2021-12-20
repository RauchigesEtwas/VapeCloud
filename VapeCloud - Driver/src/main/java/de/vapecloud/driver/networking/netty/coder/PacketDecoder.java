package de.vapecloud.driver.networking.netty.coder;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.networking.netty.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int id = byteBuf.readInt();
        //Class<? extends Packet> packetClass = Driver.getInstance().getPacketManager().getPacketFromId(id);
        Class<? extends Packet> packetClass = VapeDriver.packetManager.getIncomingPacketFromId(id);
        if(packetClass != null) {
            Packet packet = packetClass.newInstance();
            packet.decodePayload(byteBuf);
            list.add(packet);
        } else {
            System.out.println("Invalid packet: " + id);
        }
    }
}
