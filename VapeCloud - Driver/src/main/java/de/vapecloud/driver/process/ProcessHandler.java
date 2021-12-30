package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 31.12.2021/00:19
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class ProcessHandler {

    private HashMap<String, ArrayList<String>> runningProcesses;
    private HashMap<String, ContainerProcess> runningContainerProcesses;

    public ProcessHandler() {
        this.runningProcesses =  new HashMap<>();
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
