package de.vapecloud.driver.networking.packets.cloudprocess.out;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/13:11
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class CloudProcessConnecedPacket extends Packet {

    private String processName, processType, runningCluster;
    private int port;

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("processname", this.processName);
        buffer.write("processtype", this.processType);
        buffer.write("port", this.port);

    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.processName = buffer.read("processname", String.class);
        this.processType = buffer.read("processtype", String.class);
        this.port = buffer.read("port", Integer.class);
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRunningCluster() {
        return runningCluster;
    }

    public void setRunningCluster(String runningCluster) {
        this.runningCluster = runningCluster;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
