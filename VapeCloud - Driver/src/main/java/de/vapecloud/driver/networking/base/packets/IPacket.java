package de.vapecloud.driver.networking.base.packets;
/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

import io.netty.buffer.ByteBuf;

public interface IPacket {

    void encodePayload(ByteBuf byteBuf);
    void decodePayload(ByteBuf byteBuf);

}
