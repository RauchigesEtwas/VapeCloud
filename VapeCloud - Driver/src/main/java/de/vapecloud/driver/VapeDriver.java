package de.vapecloud.driver;


import de.vapecloud.driver.console.IConsolHandler;
import de.vapecloud.driver.networking.netty.packets.PacketManager;
import de.vapecloud.driver.utils.IDataCenter;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeDriver {

    public static boolean inSetup;
    public static IDataCenter dataCenter = new IDataCenter();
    public static IConsolHandler consolHandler;
    public static PacketManager packetManager = new PacketManager();

}
