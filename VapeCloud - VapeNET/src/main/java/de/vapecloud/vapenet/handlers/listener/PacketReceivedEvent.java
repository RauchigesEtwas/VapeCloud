package de.vapecloud.vapenet.handlers.listener;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:13
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.IPacketEvent;
import de.vapecloud.vapenet.protocol.Packet;

public class PacketReceivedEvent implements IPacketEvent {

    private VapeNETChannel channel;
    private Packet packet;

    public PacketReceivedEvent(VapeNETChannel channel, Packet packet) {
        this.channel = channel;
        this.packet = packet;
    }

    public VapeNETChannel getChannel() {
        return channel;
    }

    public Packet getPacket() {
        return packet;
    }
}
