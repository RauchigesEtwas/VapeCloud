package de.vapecloud.launcher.manager;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.driver.configuration.ConfigHandler;

public class VapeManager {

    public void initVapeManager(){


        new ConfigHandler("./test.json").saveConfig(new testConfig("test"));
        testConfig testConfig = (testConfig) new ConfigHandler("./test.json").getConfig(testConfig.class);

        System.out.println(testConfig.getTest());

        while (true){}

    }

}
