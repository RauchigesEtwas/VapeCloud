package de.vapecloud.vapenet.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:06
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;
import java.util.List;

public class PacketManager {
    private List<PacketListener> listeners;


    public PacketManager() {
        this.listeners = new ArrayList<>();
    }
    public List<PacketListener> getAllListeners(){
        return listeners;
    }
    public void addPacketHandler(PacketListener listener){
        listeners.add(listener);
    }

}
