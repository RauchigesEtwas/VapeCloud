package de.vapecloud.driver.networking.packets.cluster.in;

/*
 * Projectname: VapeCloud
 * Created AT: 07.01.2022/16:10
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class ShutdownAllPacket extends Packet {

    public ShutdownAllPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
    }

    @Override
    public void read(IPacketBuffer buffer) {

    }
}
