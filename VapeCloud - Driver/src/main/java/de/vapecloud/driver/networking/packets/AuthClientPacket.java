package de.vapecloud.driver.networking.packets;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthClientPacket extends Packet {

    private String clientName;
    private String key;
    private boolean isClustered;
    private String ClusterAuthKey;

    public AuthClientPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("clientName", clientName);
        buffer.write("key", key);
        buffer.write("isClustered", isClustered);
        buffer.write("ClusterAuthKey", ClusterAuthKey);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        clientName = buffer.read("clientName", String.class);
        key = buffer.read("key", String.class);
        isClustered = buffer.read("isClustered", Boolean.class);
        ClusterAuthKey = buffer.read("ClusterAuthKey", String.class);
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isClustered() {
        return isClustered;
    }

    public void setClustered(boolean clustered) {
        isClustered = clustered;
    }

    public String getClusterAuthKey() {
        return ClusterAuthKey;
    }

    public void setClusterAuthKey(String clusterAuthKey) {
        ClusterAuthKey = clusterAuthKey;
    }
}
