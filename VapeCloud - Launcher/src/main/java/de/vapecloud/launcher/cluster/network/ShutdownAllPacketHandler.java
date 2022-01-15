package de.vapecloud.launcher.cluster.network;

/*
 * Projectname: VapeCloud
 * Created AT: 07.01.2022/16:13
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.packets.cluster.in.ShutdownAllPacket;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;

public class ShutdownAllPacketHandler extends PacketListener {

    @PacketProvideHandler(priority = 100)
    public void handleShutdown(PacketReceivedEvent event){
        Packet packet = event.getPacket();
        VapeNETChannel channel = event.getChannel();
        if (packet instanceof ShutdownAllPacket){
            System.exit(0);
        }
    }
}
