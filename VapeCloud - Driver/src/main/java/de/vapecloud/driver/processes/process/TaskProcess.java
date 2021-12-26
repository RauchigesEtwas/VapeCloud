package de.vapecloud.driver.processes.process;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/16:18
 * Created by Robin B. (RauchigesEtwas)
 */

public class TaskProcess {

    String taskName, taskPath, runningWrapperIP, ManagerIP;
    Integer port;

    public TaskProcess(String taskName, String taskPath, String runningWrapperIP, String managerIP, Integer port) {
        this.taskName = taskName;
        this.taskPath = taskPath;
        this.runningWrapperIP = runningWrapperIP;
        ManagerIP = managerIP;
        this.port = port;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskPath() {
        return taskPath;
    }

    public String getRunningWrapperIP() {
        return runningWrapperIP;
    }

    public String getManagerIP() {
        return ManagerIP;
    }

    public Integer getPort() {
        return port;
    }
}
