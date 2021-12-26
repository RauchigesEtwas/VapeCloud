package de.vapecloud.driver;


import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.driver.networking.NetworkManager;
import de.vapecloud.driver.processes.ProcessManager;
import de.vapecloud.driver.utils.DataCenter;
import de.vapecloud.driver.utils.VapeSettings;


/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeDriver {

    public static DataCenter dataCenter = new DataCenter();
    public static ConsolHandler consolHandler;
    public static NetworkManager networkManager = new NetworkManager();
    public static ProcessManager processManager = new ProcessManager();
    public static VapeSettings vapeSettings = new VapeSettings();

}
