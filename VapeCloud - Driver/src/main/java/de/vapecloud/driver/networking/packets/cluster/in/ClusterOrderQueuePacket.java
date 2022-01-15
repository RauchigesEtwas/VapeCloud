package de.vapecloud.driver.networking.packets.cluster.in;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/12:06
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.ArrayList;
import java.util.List;

public class ClusterOrderQueuePacket extends Packet {


    private String processName, runningCluster, processVersion, processModeType, runningPath, splitter;
    private Boolean staticProcess, sameAddress, submitToQueue;
    private Integer selectedID, minimalMemory, maximalMemory, provideStartPort;


    public ClusterOrderQueuePacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("processname", this.processName);
        buffer.write("splitter", this.splitter);
        buffer.write("runningcluster", this.runningCluster);
        buffer.write("processversion", this.processVersion);
        buffer.write("processmodetype", this.processModeType);
        buffer.write("runningpath", this.runningPath);
        buffer.write("submittoqueue", this.submitToQueue);
        buffer.write("sameaddress", this.sameAddress);
        buffer.write("staticprocess", this.staticProcess);
        buffer.write("selectedid", this.selectedID);
        buffer.write("minimalmemory", this.minimalMemory);
        buffer.write("maximalmemory", this.maximalMemory);
        buffer.write("providestartport", this.provideStartPort);
    }

    @Override
    public void read(IPacketBuffer buffer) {
       this.processName = buffer.read("processname", String.class);
       this.splitter = buffer.read("splitter", String.class);
       this.runningCluster = buffer.read("runningcluster", String.class);
       this.processVersion = buffer.read("processversion", String.class);
       this.processModeType = buffer.read("processmodetype", String.class);
       this.runningPath = buffer.read("runningpath", String.class);
       this.submitToQueue = buffer.read("submittoqueue", Boolean.class);
       this.sameAddress = buffer.read("sameaddress", Boolean.class);
       this.staticProcess = buffer.read("staticprocess", Boolean.class);
       this.selectedID = buffer.read("selectedid", Integer.class);
       this.minimalMemory = buffer.read("minimalmemory", Integer.class);
       this.maximalMemory = buffer.read("maximalmemory", Integer.class);
       this.provideStartPort = buffer.read("providestartport", Integer.class);
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public Boolean getSameAddress() {
        return sameAddress;
    }

    public Boolean getSubmitToQueue() {
        return submitToQueue;
    }

    public void setSubmitToQueue(Boolean submitToQueue) {
        this.submitToQueue = submitToQueue;
    }

    public void setSameAddress(Boolean sameAddress) {
        this.sameAddress = sameAddress;
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

    public String getProcessVersion() {
        return processVersion;
    }

    public void setProcessVersion(String processVersion) {
        this.processVersion = processVersion;
    }

    public String getProcessModeType() {
        return processModeType;
    }

    public void setProcessModeType(String processModeType) {
        this.processModeType = processModeType;
    }

    public String getRunningPath() {
        return runningPath;
    }

    public void setRunningPath(String runningPath) {
        this.runningPath = runningPath;
    }

    public Boolean getStaticProcess() {
        return staticProcess;
    }

    public void setStaticProcess(Boolean staticProcess) {
        this.staticProcess = staticProcess;
    }

    public Integer getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(Integer selectedID) {
        this.selectedID = selectedID;
    }

    public Integer getMinimalMemory() {
        return minimalMemory;
    }

    public void setMinimalMemory(Integer minimalMemory) {
        this.minimalMemory = minimalMemory;
    }

    public Integer getMaximalMemory() {
        return maximalMemory;
    }

    public void setMaximalMemory(Integer maximalMemory) {
        this.maximalMemory = maximalMemory;
    }

    public Integer getProvideStartPort() {
        return provideStartPort;
    }

    public void setProvideStartPort(Integer provideStartPort) {
        this.provideStartPort = provideStartPort;
    }
}
