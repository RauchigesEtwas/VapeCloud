package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/16:30
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.utils.setup.SetupData;

import java.util.HashMap;

public class VapeSettings {

    public VapeSettings() {}
    private SetupData setupData = new SetupData();
    private boolean runningaCluster = false;
    private String cloudVersion = "Earthquake-1.0.0";

    public SetupData getSetupData() {
        return setupData;
    }

    public String getCloudVersion() {
        return cloudVersion;
    }

    public boolean isRunningaCluster() {
        return runningaCluster;
    }

    public void setRunningaCluster(boolean runningaCluster) {
        this.runningaCluster = runningaCluster;
    }
}
