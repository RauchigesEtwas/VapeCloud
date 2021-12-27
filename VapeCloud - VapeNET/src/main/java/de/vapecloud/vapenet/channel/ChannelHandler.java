package de.vapecloud.vapenet.channel;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:39
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.Packet;

import java.io.IOException;

public class ChannelHandler {


    public void handleConnected(IChannel channel) throws IOException {}

    public void handlePacket(IChannel channel, Packet packet) throws IOException {}

    public void handleDisconnected(IChannel channel) throws IOException {}

    public void handleException(Exception exception) {
        exception.printStackTrace();
    }
}
