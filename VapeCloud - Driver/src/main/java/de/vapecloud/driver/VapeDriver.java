package de.vapecloud.driver;


import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.driver.events.EventExecutor;
import de.vapecloud.driver.events.EventHandler;
import de.vapecloud.driver.modules.ModuleHandler;
import de.vapecloud.driver.networking.NetworkHandler;
import de.vapecloud.driver.process.ProcessHandler;
import de.vapecloud.driver.utils.VapeSettings;



/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeDriver {


    private static VapeDriver instance;
    private  ConsolHandler consolHandler;
    private  VapeSettings vapeSettings;
    private NetworkHandler networkHandler;
    private ModuleHandler moduleHandler;
    private ProcessHandler processHandler;
    private EventExecutor eventExecutor;

    public VapeDriver() {
        this.instance = this;
        this.networkHandler = new NetworkHandler();
        this.vapeSettings = new VapeSettings();
        this.moduleHandler = new ModuleHandler();
        this.processHandler = new ProcessHandler();
        this.eventExecutor = new EventExecutor();
    }

    public ProcessHandler getProcessHandler() {
        return processHandler;
    }

    public ModuleHandler getModuleHandler() {
        return moduleHandler;
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


    public VapeSettings getVapeSettings() {
        return vapeSettings;
    }


}
