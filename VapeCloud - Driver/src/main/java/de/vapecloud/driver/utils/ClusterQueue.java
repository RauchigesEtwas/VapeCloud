package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/13:54
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.packets.cluster.in.ClusterOrderQueuePacket;

import java.util.ArrayList;

public class ClusterQueue {

    private ArrayList<ClusterOrderQueuePacket> processQueue;

    public ClusterQueue() {
        processQueue = new ArrayList<>();
    }

    public ArrayList<ClusterOrderQueuePacket> getProcessQueue() {
        return processQueue;
    }
}
