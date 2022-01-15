package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/09:40
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.driver.process.bin.ServerData;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessHandler {

    private HashMap<String, HashMap<String,Boolean>> runningProcessByCluster;
    private ArrayList< Integer> blockedPortsFromCluster;
    private ArrayList<ServerData> connectedServers;
    private HashMap<String, ArrayList<Integer>>  blockedID;
    private HashMap<String, ArrayList<RunningProcess>> processesByGroup;

    public ProcessHandler() {
        this.runningProcessByCluster = new HashMap<>();
        this.blockedPortsFromCluster = new ArrayList<>();
        this.processesByGroup = new HashMap<>();
        this.connectedServers = new ArrayList<>();
        this.blockedID = new HashMap<>();
    }

    public ArrayList<ServerData> getConnectedServers() {
        return connectedServers;
    }

    public void setConnectedServers(ArrayList<ServerData> connectedServers) {
        this.connectedServers = connectedServers;
    }

    public void removeID(String container, Integer id){
        if (this.blockedID.get(container).contains(id)){
            this.blockedID.get(container).remove(id);
        }
    }

    public Integer getFreeID(String container){
        if(!this.blockedID.containsKey(container)){
            this.blockedID.put(container, new ArrayList<>());
        }
        int resuls = 0;
        ContainerHandler containerHandler = new ContainerHandler();
        SubContainer subContainer = containerHandler.getSubContainers(container);
        int maxStart = subContainer.getMaximalOnline();

        if(maxStart < 0){
            maxStart = 900000;
            for (int i = 1; i != maxStart; i++){
                if (!this.blockedID.get(container).contains(i)){
                    resuls = i;
                    this.blockedID.get(container).add(resuls);
                    i = maxStart;
                    return resuls;
                }
            }
        }else {
            for (int i = 1; i != maxStart; i++){
                if (!this.blockedID.get(container).contains(i)){
                    resuls = i;
                    this.blockedID.get(container).add(resuls);
                    i = maxStart;
                    return resuls;
                }
            }
        }
        return 0;
    }

    public ArrayList<RunningProcess> getProcessByGroup(String group){
        return this.processesByGroup.get(group);
    }

    public RunningProcess getProcess(String processName){
        if (this.processesByGroup != null){
            RunningProcess  runningProcess = null;
            for (String group : this.processesByGroup.keySet() ){
                for (int i = 0; i != this.processesByGroup.get(group).size() ; i++) {
                        if (this.processesByGroup.get(group).get(i).getProcessCore().getProcessName().equalsIgnoreCase(processName)){
                            Integer id = i;
                            i = this.processesByGroup.get(group).size();
                            return this.processesByGroup.get(group).get(id);
                        }
                }
            }

        }
        return null;
    }

    public void removeProcess(String processName){
        if (this.processesByGroup != null){
            this.processesByGroup.forEach((s, runningProcesses) -> {
                runningProcesses.forEach(runningProcess -> {
                    if (runningProcess.getProcessCore().getProcessName().equalsIgnoreCase(processName)){
                        runningProcess.stopProcess();
                        this.processesByGroup.get(s).remove(runningProcess);
                    }
                });
            });
        }
    }

    public void addProcess(String group,  ProcessCore process){
        if (this.processesByGroup != null){
            if (this.processesByGroup.containsKey(group)){
                ArrayList<ProcessCore> bp = new ArrayList<>();
                this.processesByGroup.get(group).forEach(runningProcess -> {bp.add(runningProcess.getProcessCore());});
                if (!bp.contains(process)){
                    RunningProcess runningProcess = new RunningProcess();
                    runningProcess.setProcessCore(process);
                    this.processesByGroup.get(group).add(runningProcess);
                }
            }else {
                this.processesByGroup.put(group, new ArrayList<>());
                addProcess(group, process);
            }
        }else {
            this.processesByGroup = new HashMap<>();
            addProcess(group, process);
        }

    }

    public void removeUsedPort(Integer port){
        if (this.blockedPortsFromCluster != null){
                if (this.blockedPortsFromCluster.contains(port)){
                    this.blockedPortsFromCluster.remove(port);
                }
        }else {
            this.blockedPortsFromCluster = new ArrayList<>();

        }
    }

    public Integer getFreePort(int startport){
        if (this.blockedPortsFromCluster != null){
            int resuls = 0;
            for (int i = startport; i > startport-1 ; i++) {
                if (!this.blockedPortsFromCluster.contains(i)){
                    addUsedPort(i);
                    return i;
                }
            }
        }else {
            this.blockedPortsFromCluster = new ArrayList<>();
            return getFreePort(startport);
        }
        return 0;
    }

    public ArrayList<Integer> getUsedPorts(){
        if (this.blockedPortsFromCluster != null){
            return this.blockedPortsFromCluster;
        }else {
            return null;
        }
    }

    public void addUsedPort( Integer port){
        if (this.blockedPortsFromCluster != null){
               if (! this.blockedPortsFromCluster.contains(port)){
                   this.blockedPortsFromCluster.add(port);
               }
        }else {
            this.blockedPortsFromCluster = new ArrayList<>();
            addUsedPort(port);
        }
    }

    public void addConnectedCluster(String clusterName){
        if(this.runningProcessByCluster != null){
            if(!this.getConnectedClusters().contains(clusterName)){
                this.runningProcessByCluster.put(clusterName, new HashMap<>());
            }
        }else {
            this.runningProcessByCluster = new HashMap<>();
            addConnectedCluster(clusterName);
        }
    }

    public ArrayList<String> disconnectCluster(String cluster){
        ArrayList<String> processe = new ArrayList<>();
        if(this.runningProcessByCluster != null){
            if (getConnectedClusters().contains(cluster)){
                this.runningProcessByCluster.get(cluster).forEach((process, connected) -> {
                    processe.add(process);
                });
            }
        }else {
            this.runningProcessByCluster = new HashMap<>();
        }
      return processe;
    }

    public ArrayList<String> getConnectedClusters() {
        ArrayList<String> clusters = new ArrayList<>();
        if(this.runningProcessByCluster != null){

            this.runningProcessByCluster.forEach((s, stringBooleanHashMap) -> {
                clusters.add(s);
            });

        }else{
            this.runningProcessByCluster = new HashMap<>();
            getConnectedClusters();
        }
        return clusters;
    }

    public void removeProcessFromCluster(String process){
        if(this.runningProcessByCluster != null){
            this.runningProcessByCluster.forEach((cluster, process1) -> {
              if (process1.containsKey(process)){
                  process1.remove(process);
              }
            });
        }else {
            this.runningProcessByCluster = new HashMap<>();
        }
    }

    public void addProcessToCluster(String cluster, String process){
        if(this.runningProcessByCluster != null){
            if (this.getConnectedClusters().contains(cluster)){
                if (! this.runningProcessByCluster.get(cluster).containsKey(process)){
                    this.runningProcessByCluster.get(cluster).put(process, false);
                }
            }
        }else {
            this.runningProcessByCluster = new HashMap<>();
        }
    }

    public HashMap<String, Boolean> getProcessFromCluster(String cluster){
        if(this.runningProcessByCluster != null){
            if (getConnectedClusters().contains(cluster)){
                return this.runningProcessByCluster.get(cluster);
            }else {
                return null;
            }
        }else {
            this.runningProcessByCluster = new HashMap<>();
            return null;
        }
    }

    public void changeConnectionStatusFromProcess(String process){
        if(this.runningProcessByCluster != null){
            ServiceConfig serviceConfig = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);

            runningProcessByCluster.get(new ContainerHandler().getSubContainers(process.split(serviceConfig.getInternalSplitter())[0]).getRunningCluster()).remove(process);
            runningProcessByCluster.get(new ContainerHandler().getSubContainers(process.split(serviceConfig.getInternalSplitter())[0]).getRunningCluster()).put(process, true);
        }else {
            this.runningProcessByCluster = new HashMap<>();
        }
    }

    public boolean getConnectedStatus(String process){
        if(this.runningProcessByCluster != null){
            ServiceConfig serviceConfig = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
            return runningProcessByCluster.get(new ContainerHandler().getSubContainers(process.split(serviceConfig.getInternalSplitter())[0]).getRunningCluster()).get(process);
        }else {
            return false;
        }
    }

    public HashMap<String, ArrayList<RunningProcess>> getProcessesByGroup() {
        return processesByGroup;
    }
}
