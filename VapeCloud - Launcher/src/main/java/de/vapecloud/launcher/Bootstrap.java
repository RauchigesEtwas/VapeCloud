package de.vapecloud.launcher;
/*
 * Created AT: 13.11.2021
 * Created by Robin B. (UniqueByte)
 */


import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.IConsolHandler;
import de.vapecloud.launcher.manager.VapeManager;

public class Bootstrap {


    public static void main(String[] args) {
        if (VapeDriver.consolHandler == null){
            VapeDriver.consolHandler = new IConsolHandler();
            VapeDriver.consolHandler.createHandel("CONSOLE");
        }

        VapeDriver.consolHandler.getiLogger().empty(false, VapeDriver.dataCenter.getLogo(), null);

        new VapeManager().InitVapeMaster();
    }
}
