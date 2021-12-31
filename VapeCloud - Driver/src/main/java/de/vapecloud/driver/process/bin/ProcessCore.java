package de.vapecloud.driver.process.bin;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/14:13
 * Created by Robin B. (RauchigesEtwas)
 */

public class ProcessCore {

    private String processName;

    private String runningCluster;

    private String processVersion;

    private String processModeType;

    private String runningPath;

    private boolean staticProcess;

    private Integer selectedID;

    private Integer minimalMemory;

    private Integer maximalMemory;

    private Integer provideStartPort;

    public ProcessCore() { }


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

    public boolean isStaticProcess() {
        return staticProcess;
    }

    public void setStaticProcess(boolean staticProcess) {
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
