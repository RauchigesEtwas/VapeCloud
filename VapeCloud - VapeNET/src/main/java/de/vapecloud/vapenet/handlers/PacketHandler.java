package de.vapecloud.vapenet.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:04
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.protocol.Packet;

public class PacketHandler {

    public void handleConnected(IChannel channel) {}
    public void handlePacket(IChannel channel, Packet packet) {}
    public void handleDisconnected(IChannel channel) {}
    public void handleException(Exception exception) {}

}
