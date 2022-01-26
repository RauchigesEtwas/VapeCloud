package de.vapecloud.driver.networking.packets.cloudprocess.in;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/16:16
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.HashMap;

public class ProcessUpdatePacket extends Packet {

    private String managerAddress;
    private Integer port, processStartPort;
    private String processName;
    private String runningCluster;
    private String processMode;
    private HashMap<String, String> messages;

    @Override
    public void write(IPacketBuffer buffer) {
    }

   @Override
   public void read(IPacketBuffer buffer) {
    }

    public String getManagerAddress() {
        return managerAddress;
    }

    public void setManagerAddress(String managerAddress) {
        this.managerAddress = managerAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getProcessStartPort() {
        return processStartPort;
    }

    public void setProcessStartPort(Integer processStartPort) {
        this.processStartPort = processStartPort;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getRunningCluster() {
        return runningCluster;
    }

    public void setRunningCluster(String runningCluster) {
        this.runningCluster = runningCluster;
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
}