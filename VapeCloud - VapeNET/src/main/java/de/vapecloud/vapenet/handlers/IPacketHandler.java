package de.vapecloud.vapenet.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:04
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.protocol.Packet;

public interface IPacketHandler {

    void handleConnected(IChannel channel);
    void handlePacket(IChannel channel, Packet packet);
    void handleDisconnected(IChannel channel);
    void handleException(Exception exception);

}
