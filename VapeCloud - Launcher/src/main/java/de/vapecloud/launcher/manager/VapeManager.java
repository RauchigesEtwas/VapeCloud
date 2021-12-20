package de.vapecloud.launcher.manager;
/*
 * Created AT: 13.11.2021
 * Created by Robin B. (UniqueByte)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.IConfigHandler;
import de.vapecloud.driver.console.IConsolHandler;
import de.vapecloud.launcher.manager.configuration.TestConfig;

public class VapeManager {

    public void InitVapeMaster(){
        if (VapeDriver.consolHandler == null){
            VapeDriver.consolHandler = new IConsolHandler();
            VapeDriver.consolHandler.createHandel("CONSOLE");
        }


    }

}
