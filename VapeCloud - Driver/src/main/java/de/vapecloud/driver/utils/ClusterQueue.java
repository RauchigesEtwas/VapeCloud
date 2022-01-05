package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/13:54
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.packets.cluster.to.ClusterOrderQueuePacket;
import de.vapecloud.vapenet.protocol.Packet;

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
