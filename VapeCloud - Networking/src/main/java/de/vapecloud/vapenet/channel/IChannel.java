package de.vapecloud.vapenet.channel;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:39
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketSender;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;

public interface IChannel extends Closeable, IPacketSender {

    @Override
    void close() throws IOException;

    @Override
    void sendPacket(Packet packet);

    boolean isConnected();

    ChannelPipeline getPipeline();

    SocketAddress getRemoteAddress();

    InetAddress getLocalAddress();

}
