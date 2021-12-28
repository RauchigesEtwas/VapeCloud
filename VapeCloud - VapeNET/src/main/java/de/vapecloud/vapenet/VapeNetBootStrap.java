package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/23:36
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.handlers.PacketManager;

public class VapeNetBootStrap {

    private static VapeNetBootStrap instance;
    public PacketManager packetManager;
    public VapeNETServer server;
    public VapeNETClient client;

    public VapeNetBootStrap() {
        packetManager = new PacketManager();
        instance = this;
    }

    public static VapeNetBootStrap getInstance() {
        return instance;
    }


}
