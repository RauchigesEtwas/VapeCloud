package de.vapecloud.vapenet.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:06
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;
import java.util.List;

public class PacketManager {
    private List<PacketHandler> listeners;


    public PacketManager() {
        this.listeners = new ArrayList<>();
    }
    public List<PacketHandler> getAllListeners(){
        return listeners;
    }
    public void addPacketHandler(PacketHandler listener){
        listeners.add(listener);
    }

}
