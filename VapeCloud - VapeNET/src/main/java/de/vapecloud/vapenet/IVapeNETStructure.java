package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.IChannelInitializer;
import de.vapecloud.vapenet.protocol.IPacketSender;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.Closeable;
import java.io.IOException;

public interface IVapeNETStructure extends Closeable, IPacketSender {

    @Override
    void close() throws IOException;

    @Override
    void sendPacket(Packet packet);

    IVapeNETStructure init(IChannelInitializer initializer);

    IVapeNETStructure option(VapeNETOption<?> option, Object value);

    <T> T getOption(VapeNETOption<T> option);

    boolean isConnected();
}
