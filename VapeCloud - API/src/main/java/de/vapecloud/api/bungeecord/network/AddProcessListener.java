package de.vapecloud.api.bungeecord.network;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/14:01
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.api.bungeecord.BungeeAPILaunch;
import de.vapecloud.api.bungeecord.utils.serverhelper.ServerHelper;
import de.vapecloud.driver.networking.packets.cloudprocess.in.ProcessBindeToBungeePacket;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;
import net.md_5.bungee.api.ProxyServer;

import java.net.InetSocketAddress;

public class AddProcessListener extends PacketListener {

    @PacketProvideHandler(priority = 100)
    public void handleProcessAdd(PacketReceivedEvent event){
        Packet packets = event.getPacket();
        if (packets instanceof ProcessBindeToBungeePacket){
            ProcessBindeToBungeePacket packet = (ProcessBindeToBungeePacket) packets;
            System.out.println("test");

            ProxyServer.getInstance().getConsole().sendMessage("§bVape§fCloud §8| §7add gamserver: " + packet.getProcessName() + "~" + packet.getHostAddress() + ":"+packet.getPort());

            if (!ServerHelper.serverExists(packet.getProcessName())){
                ServerHelper.addServer(ProxyServer.getInstance().constructServerInfo(packet.getProcessName(), new InetSocketAddress(packet.getHostAddress().replace("/", ""), packet.getPort()), "VapeCloud-GameServer", false));

                String mode = packet.getProcessMode();
                if (mode.equalsIgnoreCase("LOBBY")){
                    BungeeAPILaunch.getInstance().Lobbys.add(packet.getProcessName());
                }

            }

        }
    }
}
