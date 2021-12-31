package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 31.12.2021/00:19
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.container.ContainerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class ProcessHandler {

    private HashMap<String, ArrayList<String>> runningProcesses;
    private Integer running = 0;
    private HashMap<String, ContainerProcess> runningContainerProcesses;
    private HashMap<String, ArrayList<Integer>> containerID;

    public ProcessHandler() {
        this.runningProcesses =  new HashMap<>();
        this.runningContainerProcesses = new HashMap<>();
        this.containerID = new HashMap<>();
    }

    public void addProcess(String process, ContainerProcess containerProcess){
        if (!runningContainerProcesses.containsKey(process)){
            runningContainerProcesses.put(process, containerProcess);
        }
    }
    public void removeProcess(String process){
        if (runningContainerProcesses.containsKey(process)){
            runningContainerProcesses.remove(process);
        }
    }


    public Integer getFreeID(String container){
        ContainerHandler containerHandler = new ContainerHandler();

        int maxServer = containerHandler.getSubContainers(container).getMaximalOnline();
        if(maxServer < 0){
            maxServer = Integer.MAX_VALUE;
            for (int i = 0; i != maxServer; i++){
                if (!getUsedIDs(container).contains(i)){
                    Integer id = i;
                    i = maxServer;
                    return id;
                }
            }
        }else{
            for (int i = 0; i != maxServer; i++){
                if (!getUsedIDs(container).contains(i)){
                    Integer id = i;
                    i = maxServer;
                    return id;
                }
            }
        }
        return 0;
    }

    public void removeID(String container, Integer id){
        if (containerID.containsKey(container) && containerID.get(container).contains(id)){
            containerID.get(container).remove(id);
        }
    }


    public void addID(String container, Integer id){
        if (containerID.containsKey(container) && !containerID.get(container).contains(id)){
            containerID.get(container).add(id);
        }else{
            containerID.put(container, new ArrayList<>());
            addID(container, id);
        }
    }

    public ArrayList<Integer> getUsedIDs(String container){
        if (containerID.containsKey(container)){
            return containerID.get(container);
        }
        return null;
    }

    public Integer howMutchRunning(String container){
        ContainerHandler containerHandler = new ContainerHandler();
        this.running = 0;
        runningProcesses.get(containerHandler.getSubContainers(container).getRunningCluster()).forEach(s -> {
            if (s.startsWith(containerHandler.getSubContainers(container).getContainerName())){
                this.running++;
            }
        });
        return this.running;
    }

    public ContainerProcess getProcess(String process){
        if (runningContainerProcesses.containsKey(process)){
            return runningContainerProcesses.get(process);
        }
        return null;
    }

    public ArrayList<String> getProcesseFromCluster(String cluster){
        if (this.runningProcesses.containsKey(cluster)){
            return  runningProcesses.get(cluster);
        }else {
            return null;
        }
    }
    public String getClusterFormProcess(String process){
        final String[] resuls = {null};
        runningProcesses.forEach((s, strings) -> {
            if (strings.contains(process)){
                resuls[0] = s;
            }
        });
        return resuls[0];
    }

    public void unregisterProcessFromeCluster(String process){
        runningProcesses.forEach((s, strings) -> {
          if (strings.contains(process)){
              this.runningProcesses.get(s).remove(process);
              return;
          }
        });
    }

    public void registerProcessToCluster(String cluster, String process){
        if(isClusterFinde(cluster)){
            if (!this.runningProcesses.get(cluster).contains(process)){
                this.runningProcesses.get(cluster).add(process);
            }
        }
    }

    public boolean isClusterFinde(String cluster){
        if (this.runningProcesses.containsKey(cluster)){
            return true;
        }
        return false;
    }
}
