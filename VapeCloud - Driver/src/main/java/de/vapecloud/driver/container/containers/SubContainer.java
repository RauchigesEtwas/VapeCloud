package de.vapecloud.driver.container.containers;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/11:35
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.container.enums.ContainerModeType;
import de.vapecloud.driver.container.enums.ContainerVersion;

public class SubContainer {


    private String containerName;
    private Integer totalOnline, maximalOnline, minimalMemory, maximalMemoery, maximalPlayers;
    private Boolean maintenance, staticService;
    private String joinPermission;
    private Integer containerTimeout;
    private ContainerModeType containerModeType;
    private ContainerVersion containerVersion;
    private String runningCluster;

    public SubContainer() {}


    public SubContainer(String containerName, Integer totalOnline, Integer maximalOnline, Integer minimalMemory, Integer maximalMemoery, Integer maximalPlayers, Boolean maintenance, Boolean staticService, String joinPermission, Integer containerTimeout, ContainerModeType containerModeType, ContainerVersion containerVersion, String runningCluster) {
        this.containerName = containerName;
        this.totalOnline = totalOnline;
        this.maximalOnline = maximalOnline;
        this.minimalMemory = minimalMemory;
        this.maximalMemoery = maximalMemoery;
        this.maximalPlayers = maximalPlayers;
        this.maintenance = maintenance;
        this.staticService = staticService;
        this.joinPermission = joinPermission;
        this.containerTimeout = containerTimeout;
        this.containerModeType = containerModeType;
        this.containerVersion = containerVersion;
        this.runningCluster = runningCluster;
    }

    public String getContainerName() {
        return containerName;
    }


    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setTotalOnline(Integer totalOnline) {
        this.totalOnline = totalOnline;
    }

    public void setMaximalOnline(Integer maximalOnline) {
        this.maximalOnline = maximalOnline;
    }

    public void setMinimalMemory(Integer minimalMemory) {
        this.minimalMemory = minimalMemory;
    }

    public void setMaximalMemoery(Integer maximalMemoery) {
        this.maximalMemoery = maximalMemoery;
    }

    public void setMaximalPlayers(Integer maximalPlayers) {
        this.maximalPlayers = maximalPlayers;
    }

    public void setMaintenance(Boolean maintenance) {
        this.maintenance = maintenance;
    }

    public void setStaticService(Boolean staticService) {
        this.staticService = staticService;
    }

    public void setJoinPermission(String joinPermission) {
        this.joinPermission = joinPermission;
    }

    public void setContainerTimeout(Integer containerTimeout) {
        this.containerTimeout = containerTimeout;
    }

    public void setContainerModeType(ContainerModeType containerModeType) {
        this.containerModeType = containerModeType;
    }

    public void setContainerVersion(ContainerVersion containerVersion) {
        this.containerVersion = containerVersion;
    }

    public void setRunningCluster(String runningCluster) {
        this.runningCluster = runningCluster;
    }

    public Integer getTotalOnline() {
        return totalOnline;
    }

    public Integer getMaximalOnline() {
        return maximalOnline;
    }

    public Integer getMinimalMemory() {
        return minimalMemory;
    }

    public Integer getMaximalMemoery() {
        return maximalMemoery;
    }

    public Integer getMaximalPlayers() {
        return maximalPlayers;
    }

    public Boolean getMaintenance() {
        return maintenance;
    }

    public Boolean getStaticService() {
        return staticService;
    }

    public String getJoinPermission() {
        return joinPermission;
    }

    public Integer getContainerTimeout() {
        return containerTimeout;
    }

    public ContainerModeType getContainerModeType() {
        return containerModeType;
    }

    public ContainerVersion getContainerVersion() {
        return containerVersion;
    }

    public String getRunningCluster() {
        return runningCluster;
    }
}
