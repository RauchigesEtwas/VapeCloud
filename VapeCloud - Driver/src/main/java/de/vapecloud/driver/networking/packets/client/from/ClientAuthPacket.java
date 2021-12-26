package de.vapecloud.driver.networking.packets.client.from;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/00:27
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.base.packets.Packet;
import io.netty.buffer.ByteBuf;

public class ClientAuthPacket extends Packet {


    protected ClientAuthPacket(int id) {
        super(id);
    }

    @Override
    public void writePayload(ByteBuf packet) {}

    @Override
    public void readPayload(ByteBuf packet) {}
}
