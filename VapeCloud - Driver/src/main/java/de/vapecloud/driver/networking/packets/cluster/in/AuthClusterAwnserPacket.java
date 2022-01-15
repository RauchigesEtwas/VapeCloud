package de.vapecloud.driver.networking.packets.cluster.in;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/10:36
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthClusterAwnserPacket extends Packet {


    private boolean connectionAccept;
    private String reason;

    public AuthClusterAwnserPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("connectionaccept", this.connectionAccept);
        buffer.write("reason", this.reason);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.connectionAccept = buffer.read("connectionaccept", Boolean.class);
        this.reason = buffer.read("reason", String.class);
    }

    public boolean isConnectionAccept() {
        return connectionAccept;
    }

    public void setConnectionAccept(boolean connectionAccept) {
        this.connectionAccept = connectionAccept;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
