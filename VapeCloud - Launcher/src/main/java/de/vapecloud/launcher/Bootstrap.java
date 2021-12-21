package de.vapecloud.launcher;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.launcher.manager.VapeManager;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class Bootstrap {


    public static void main(String[] args) {
        if (VapeDriver.consolHandler == null){
            VapeDriver.consolHandler = new ConsolHandler();
            VapeDriver.consolHandler.createHandel("CONSOLE");
        }

        VapeDriver.consolHandler.getLogger().empty(false, VapeDriver.dataCenter.getLogo());
        new VapeManager().initVapeManager();
    }
}
