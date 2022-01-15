package de.vapecloud.driver.configuration.configs;

/*
 * Projectname: VapeCloud
 * Created AT: 07.01.2022/18:28
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.IConfig;

import java.util.HashMap;

public class ProcessConfig implements IConfig {

    private String managerAddress;
    private Integer port, processStartPort;
    private String processName;
    private String runningCluster;
    private String processMode;
    private HashMap<String, String> messages;

    public ProcessConfig() {
    }

    public Integer getProcessStartPort() {
        return processStartPort;
    }

    public void setProcessStartPort(Integer processStartPort) {
        this.processStartPort = processStartPort;
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


    public String getRunningCluster() {
        return runningCluster;
    }

    public void setRunningCluster(String runningCluster) {
        this.runningCluster = runningCluster;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
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
}
