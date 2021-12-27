package de.vapecloud.vapenet.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/21:06
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;
import java.util.List;

public class PacketManager {
    private List<IPacketHandler> listeners;


    public PacketManager() {
        this.listeners = new ArrayList<>();
    }
    public List<IPacketHandler> getAllListeners(){
        return listeners;
    }
    public void addPacketHandler(IPacketHandler listener){
        listeners.add(listener);
    }

}
