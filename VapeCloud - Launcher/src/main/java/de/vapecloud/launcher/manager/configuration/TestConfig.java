package de.vapecloud.launcher.manager.configuration;
/*
 * Created AT: 11.12.2021
 * Created by Robin B. (UniqueByte)
 */

import de.vapecloud.driver.configuration.IConfig;

public class TestConfig implements IConfig {

    String test;


    public TestConfig() {
    }

    public TestConfig(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
