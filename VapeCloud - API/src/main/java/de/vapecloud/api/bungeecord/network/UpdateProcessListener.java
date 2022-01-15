package de.vapecloud.api.bungeecord.network;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/16:21
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.networking.packets.cloudprocess.in.ProcessUpdatePacket;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.HashMap;

public class UpdateProcessListener extends PacketListener {

    @PacketProvideHandler(priority = 200)
    public void handleUpdate(PacketReceivedEvent event){
        Packet packets = event.getPacket();
        if (packets instanceof ProcessUpdatePacket){

        }

    }
}
