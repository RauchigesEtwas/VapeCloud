package de.vapecloud.driver;


import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.driver.networking.NetworkHandler;
import de.vapecloud.driver.processes.ProcessManager;
import de.vapecloud.driver.utils.VapeSettings;


/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeDriver {


    private static VapeDriver instance;
    private  ConsolHandler consolHandler;
    private  ProcessManager processManager;
    private  VapeSettings vapeSettings;
    private NetworkHandler networkHandler;

    public VapeDriver() {
        this.instance = this;
        networkHandler = new NetworkHandler();
        this.processManager = new ProcessManager();
        this.vapeSettings = new VapeSettings();
    }


    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public static VapeDriver getInstance() {
        return instance;
    }

    public void setConsolHandler(ConsolHandler consolHandler) {
        this.consolHandler = consolHandler;
    }

    public ConsolHandler getConsolHandler() {
        return consolHandler;
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    public VapeSettings getVapeSettings() {
        return vapeSettings;
    }
}
