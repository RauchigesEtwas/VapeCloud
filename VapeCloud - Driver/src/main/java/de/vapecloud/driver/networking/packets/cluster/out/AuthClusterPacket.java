package de.vapecloud.driver.networking.packets.cluster.out;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/10:30
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthClusterPacket extends Packet {


    private String clusterName, clusterAuthentorKey;

    public AuthClusterPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("clustername", this.clusterName);
        buffer.write("clusterauthentorkey", this.clusterAuthentorKey);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.clusterName = buffer.read("clustername", String.class);
        this.clusterAuthentorKey = buffer.read("clusterauthentorkey", String.class);
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }


    public String getClusterAuthentorKey() {
        return clusterAuthentorKey;
    }

    public void setClusterAuthentorKey(String clusterAuthentorkey) {
        this.clusterAuthentorKey = clusterAuthentorkey;
    }
}
