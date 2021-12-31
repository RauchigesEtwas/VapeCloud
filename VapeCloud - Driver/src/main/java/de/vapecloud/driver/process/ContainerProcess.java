package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 31.12.2021/00:42
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.container.containers.SubContainer;

import java.lang.management.GarbageCollectorMXBean;

public class ContainerProcess {


    private String splitter;
    public Integer runningID;
    public SubContainer subContainer;

    public ContainerProcess(String splitter, Integer runningID, SubContainer subContainer) {
        this.splitter = splitter;
        this.runningID = runningID;
        this.subContainer = subContainer;
    }

    public void buildProcess(){

    }


    public void start(){

    }

    public void stop(){

    }
}
