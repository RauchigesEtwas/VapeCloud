package de.vapecloud.driver.process.bin;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/13:38
 * Created by Robin B. (RauchigesEtwas)
 */

public class ServerData {

    private String hostAddress;
    private String processName;
    private String processMode;
    private int port;

    public ServerData(String hostAddress, String processName, String processMode, int port) {
        this.hostAddress = hostAddress;
        this.processName = processName;
        this.processMode = processMode;
        this.port = port;
    }


    public String getProcessMode() {
        return processMode;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public String getProcessName() {
        return processName;
    }

    public int getPort() {
        return port;
    }
}
