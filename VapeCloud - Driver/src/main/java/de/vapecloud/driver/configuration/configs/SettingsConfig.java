package de.vapecloud.driver.configuration.configs;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/12:53
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.IConfig;

public class SettingsConfig implements IConfig {


    private String managerAddresse;
    private String runningInstance;
    private String internalAuthKey;
    private Integer internalPort;

    public SettingsConfig() {}

    public SettingsConfig(String managerAddresse, String runningInstance, String internalAuthKey, Integer internalPort) {
        this.managerAddresse = managerAddresse;
        this.runningInstance = runningInstance;
        this.internalAuthKey = internalAuthKey;
        this.internalPort = internalPort;
    }

    public String getManagerAddresse() {
        return managerAddresse;
    }

    public String getRunningInstance() {
        return runningInstance;
    }

    public String getInternalAuthKey() {
        return internalAuthKey;
    }

    public Integer getInternalPort() {
        return internalPort;
    }
}
