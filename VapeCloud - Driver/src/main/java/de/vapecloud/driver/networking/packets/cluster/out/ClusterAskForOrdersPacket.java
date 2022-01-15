package de.vapecloud.driver.networking.packets.cluster.out;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/11:08
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class ClusterAskForOrdersPacket extends Packet {


    private String clusterName;

    public ClusterAskForOrdersPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("clustername", this.clusterName);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.clusterName = buffer.read("clustername", String.class);
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
