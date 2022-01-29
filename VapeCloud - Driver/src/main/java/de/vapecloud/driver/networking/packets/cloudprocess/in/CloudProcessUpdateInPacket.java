package de.vapecloud.driver.networking.packets.cloudprocess.in;

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

public class CloudProcessUpdateInPacket extends Packet {


    private Map<String, String> data = new HashMap<>();

    public CloudProcessUpdateInPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("data", this.data);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.data =  buffer.readMap("data", String.class, String.class);
    }


    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
