package de.vapecloud.driver.networking.packets.cloudprocess.in;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/13:47
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

public class ProcessBindeToBungeePacket extends Packet {

    private String processName;
    private String hostAddress;
    private String processMode;
    private Integer port;


    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("processname", this.processName);
        buffer.write("hostaddress", this.hostAddress);
        buffer.write("processmode", this.processMode);
        buffer.write("port", this.port);
    }

    @Override
    public void read(IPacketBuffer buffer) {
        this.processName = buffer.read("processname", String.class);
        this.hostAddress = buffer.read("hostaddress", String.class);
        this.processMode = buffer.read("processmode", String.class);
        this.port = buffer.read("port", Integer.class);
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
