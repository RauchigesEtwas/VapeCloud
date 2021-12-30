package de.vapecloud.driver.container;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/11:31
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.IConfig;
import de.vapecloud.driver.container.containers.MainContainer;
import de.vapecloud.driver.container.containers.SubContainer;

import java.util.HashMap;

public class ContainerConfig implements IConfig {


    private HashMap<String, MainContainer> mainConatiner;
    private HashMap<String, SubContainer> subContainer;

    public ContainerConfig() {
        this.mainConatiner = new HashMap<>();
        this.subContainer = new HashMap<>();
    }

    public HashMap<String, MainContainer> getMainConatiners() {
        return mainConatiner;
    }

    public void setMainConatiner(HashMap<String, MainContainer> mainConatiner) {
        this.mainConatiner = mainConatiner;
    }

    public HashMap<String, SubContainer> getSubContainers() {
        return subContainer;
    }

    public void setSubContainer(HashMap<String, SubContainer> subContainer) {
        this.subContainer = subContainer;
    }
}


