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

    private DataCenter dataCenter = new DataCenter();
    private SetupData setupData = new SetupData();
    private boolean runningaCluster = false;
    private boolean likeShutdown = false;
    private String cloudVersion = "Earthquake-1.0.0";
    private Long startCount;
    private boolean isClosedByManager = false;
    private ClusterQueue queue = new ClusterQueue();

    public ClusterQueue getQueue() {
        return queue;
    }

    public boolean isClosedByManager() {
        return isClosedByManager;
    }

    public void setClosedByManager(boolean closedByManager) {
        isClosedByManager = closedByManager;
    }

    public boolean isLikeShutdown() {
        return likeShutdown;
    }

    public void setLikeShutdown(boolean likeShutdown) {
        this.likeShutdown = likeShutdown;
    }

    public Long getStartCount() {
        return startCount;
    }

    public void setStartCount(Long startCount) {
        this.startCount = startCount;
    }

    public void setRunningaCluster(boolean runningaCluster) {
        this.runningaCluster = runningaCluster;
    }

    public void setCloudVersion(String cloudVersion) {
        this.cloudVersion = cloudVersion;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    public SetupData getSetupData() {
        return setupData;
    }

    public boolean isRunningaCluster() {
        return runningaCluster;
    }

    public String getCloudVersion() {
        return cloudVersion;
    }
}
