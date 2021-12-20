package de.vapecloud.driver.networking.netty.packets;
/*
 * Created AT: 20.12.2021
 * Created by Robin B. (RauchigesEtwas)
 */

import io.netty.buffer.ByteBuf;

public interface IPacket {

    void encodePayload(ByteBuf byteBuf);
    void decodePayload(ByteBuf byteBuf);

}
