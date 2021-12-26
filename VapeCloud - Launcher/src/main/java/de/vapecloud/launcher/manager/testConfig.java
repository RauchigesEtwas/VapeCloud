package de.vapecloud.launcher.manager;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/01:47
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.IConfig;

public class testConfig implements IConfig {

    String test;

    public testConfig() {
    }

    public testConfig(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
