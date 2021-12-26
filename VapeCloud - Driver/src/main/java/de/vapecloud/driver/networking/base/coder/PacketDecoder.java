package de.vapecloud.driver.networking.base.coder;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.networking.base.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int id = byteBuf.readInt();
        Class<? extends Packet> packetClass = VapeDriver.networkManager.getPacketManager().getIncomingPacketFromId(id);
        if(packetClass != null) {
            Packet packet = packetClass.newInstance();
            packet.readPayload(byteBuf);
            list.add(packet);
        } else {
            System.out.println("Invalid packet: " + id);
        }
    }
}
