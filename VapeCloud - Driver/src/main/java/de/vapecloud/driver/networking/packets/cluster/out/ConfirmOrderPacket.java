package de.vapecloud.driver.networking.packets.cluster.out;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/14:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class ConfirmOrderPacket extends Packet {

    private String processName, cluster, processType, version;
    private Integer port;

    public ConfirmOrderPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("processname", this.processName);
        buffer.write("cluster", this.cluster);
        buffer.write("processtype", this.processType);
        buffer.write("version", this.version);
        buffer.write("port", this.port);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.processName = buffer.read("processname", String.class);
        this.cluster = buffer.read("cluster", String.class);
        this.processType = buffer.read("processtype", String.class);
        this.version = buffer.read("version", String.class);
        this.port = buffer.read("port", Integer.class);
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
