package de.vapecloud.driver.networking.packets;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:42
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthRequestPacket extends Packet {

    private String key;

    public AuthRequestPacket() {}

    public AuthRequestPacket(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("key", key);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.key = buffer.read("key", String.class);
    }
}
