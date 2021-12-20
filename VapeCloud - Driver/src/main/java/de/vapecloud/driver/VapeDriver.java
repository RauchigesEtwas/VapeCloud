package de.vapecloud.driver;
/*
 * Created AT: 13.11.2021
 * Created by Robin B. (UniqueByte)
 */

import de.vapecloud.driver.console.IConsolHandler;
import de.vapecloud.driver.utils.IDataCenter;

import java.util.Date;


public class VapeDriver {

    public static boolean inSetup;
    public static Integer setupStep;
    public static IDataCenter dataCenter = new IDataCenter();
    public static IConsolHandler consolHandler;

}
