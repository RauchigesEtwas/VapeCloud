package de.vapecloud.driver.container;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/11:34
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.container.containers.MainContainer;
import de.vapecloud.driver.container.containers.SubContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class ContainerHandler {


    private ContainerConfig config;

    public ContainerHandler(){

        if (!new ConfigHandler("./local/container.json").canFinde()){
            new ConfigHandler("./local/container.json").saveConfig( new ContainerConfig());
        }else{
            this.config = (ContainerConfig) new ConfigHandler("./local/container.json").getConfig(ContainerConfig.class);
        }
    }



    /*
     * SubContainer ADD, REMOVE, GET and EDIT
     */

    public boolean addSubContainer(String containerName, SubContainer subContainer){
        if (!this.config.getSubContainers().containsKey(containerName)){
            this.config.getSubContainers().put(containerName, subContainer);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }

    public boolean removeSubContainer(String containerName){
        if (this.config.getSubContainers().containsKey(containerName)){
            this.config.getSubContainers().remove(containerName);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }

    public ArrayList<SubContainer> getSubContainersFromCluster(String  Cluster){
        ArrayList<SubContainer> containers = new ArrayList<>();
        this.config.getSubContainers().forEach((s, subContainer) -> {
            if(subContainer.getRunningCluster().equalsIgnoreCase(Cluster)){
                containers.add(subContainer);
            }
        });
        return containers;
    }



    public ArrayList<String> getSubContainersNames(){
       ArrayList<String> containers = new ArrayList<>();
       config.getSubContainers().forEach((s, subContainer) -> {
           containers.add(s);
       });
       return containers;
    }

    public SubContainer getSubContainers(String containerName){
        if(this.config.getSubContainers().containsKey(containerName)){
            return this.config.getSubContainers().get(containerName);
        }else {
            return null;
        }
    }

    public boolean editSubContainer(String containerName, SubContainer subContainer){
        if (this.config.getSubContainers().containsKey(containerName)){
            this.config.getSubContainers().remove(containerName);
            this.config.getSubContainers().put(containerName, subContainer);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }
    public boolean canFindeSubContainer(String containerName){
        if(this.config.getSubContainers().containsKey(containerName)){
            return true;
        }else {
            return false;
        }
    }


    /*
    * MainContainer ADD, REMOVE, GET and EDIT
    */

    public boolean addMainContainer(String containerName, MainContainer mainContainer){
        if (!this.config.getMainConatiners().containsKey(containerName)){
            this.config.getMainConatiners().put(containerName, mainContainer);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }
    public ArrayList<String> getMainContainersNames(){
        ArrayList<String> containers = new ArrayList<>();
        config.getMainConatiners().forEach((s, subContainer) -> {
            containers.add(s);
        });
        return containers;
    }

    public boolean removeMainContainer(String containerName){
        if (this.config.getMainConatiners().containsKey(containerName)){
            this.config.getMainConatiners().remove(containerName);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }

    public MainContainer getMainContainer(String containerName){
        if(this.config.getMainConatiners().containsKey(containerName)){
            return this.config.getMainConatiners().get(containerName);
        }else {
            return null;
        }
    }


    public boolean canFindeMainContainer(String containerName){
        if(this.config.getMainConatiners().containsKey(containerName)){
          return true;
        }else {
            return false;
        }
    }

    public boolean editMainContainer(String containerName, MainContainer mainContainer){
        if (this.config.getMainConatiners().containsKey(containerName)){
            this.config.getMainConatiners().remove(containerName);
            this.config.getMainConatiners().put(containerName, mainContainer);
            new ConfigHandler("./local/container.json").saveConfig(this.config);
            return true;
        }
        return false;
    }


}
