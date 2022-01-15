package de.vapecloud.driver.networking.packets.cloudprocess.in;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/16:16
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class ProcessUpdatePacket extends Packet {

    private String updateType;
    private String updateText;

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("type", this.updateType);
        buffer.write("text", this.updateText);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.updateType = buffer.read("type", String.class);
        this.updateText = buffer.read("text", String.class);
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getUpdateText() {
        return updateText;
    }

    public void setUpdateText(String updateText) {
        this.updateText = updateText;
    }
}
