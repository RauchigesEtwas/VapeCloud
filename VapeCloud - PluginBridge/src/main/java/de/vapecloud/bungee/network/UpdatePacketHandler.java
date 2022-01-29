package de.vapecloud.bungee.network;

import de.vapecloud.bungee.BungeeBridge;
import de.vapecloud.bungee.utils.server.ServerManager;
import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.packets.cloudprocess.in.CloudProcessUpdateInPacket;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;
import net.md_5.bungee.api.ProxyServer;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class UpdatePacketHandler extends PacketListener {

    @PacketProvideHandler(priority = 100)
    public void handleProcessUpdate(PacketReceivedEvent event) {

        Packet packets = event.getPacket();
        try {
            new File("./test.jss").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (packets instanceof CloudProcessUpdateInPacket){
            CloudProcessUpdateInPacket update = (CloudProcessUpdateInPacket) packets;

            if (update.getData().get("updatetype").equalsIgnoreCase("UPDATEMESSAGE")){

                ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
                HashMap<String, String> messages = new HashMap<>();
                messages.remove("updatetype");
                update.getData().forEach((s, msg) -> {
                    messages.put(VapeDriver.getInstance().getVapeSettings().getDataCenter().convertToUTF8(s), VapeDriver.getInstance().getVapeSettings().getDataCenter().convertToUTF8(msg));
                });
                config.setMessages(messages);
                new ConfigHandler("./cloud/config.json").saveConfig(config);
            }else if (update.getData().get("updatetype").equalsIgnoreCase("ADDPROCESS")){
                if (!ServerManager.serverExists(update.getData().get("processname"))){
                    ServerManager.addServer(
                            ProxyServer.getInstance().constructServerInfo(update.getData().get("processname"),
                                    new InetSocketAddress(update.getData().get("processhost").replace("/", ""),
                                            Integer.valueOf(update.getData().get("processport"))),
                                    "VapeCloud-GameServer",
                                    false));


                    if (update.getData().get("processtype").equalsIgnoreCase("LOBBY")){
                        BungeeBridge.getInstance().getGeneralHandler().lobbys.add(update.getData().get("processname"));
                    }

                }
            }else if (update.getData().get("updatetype").equalsIgnoreCase("RMPROCESS")){
                if (ServerManager.serverExists(update.getData().get("processname"))){
                    ServerManager.removeServer(update.getData().get("processname"));
                }
            }else if (update.getData().get("updatetype").equalsIgnoreCase("ADDPLAYER")){

                String playerUuid =  update.getData().get("playeruuid");
                String process =  update.getData().get("currentprocess");

                if (!BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.containsKey(playerUuid)){
                    BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.put(playerUuid, process);
                    BungeeBridge.getInstance().getGeneralHandler().currentOnlineOnAllProxies++;
                }

            }else if (update.getData().get("updatetype").equalsIgnoreCase("RMPLAYER")){

                String playerUuid =  update.getData().get("playeruuid");
                if (BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.containsKey(playerUuid)){
                    BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.remove(playerUuid);
                    BungeeBridge.getInstance().getGeneralHandler().currentOnlineOnAllProxies--;
                }

            }else if (update.getData().get("updatetype").equalsIgnoreCase("UPDATEPLAYER")){

                String playerUuid =  update.getData().get("playeruuid");
                String process =  update.getData().get("currentprocess");

                if (BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.containsKey(playerUuid)){
                    BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.remove(playerUuid);
                    BungeeBridge.getInstance().getGeneralHandler().currentServerOfPlayers.put(playerUuid, process);
                }

            }
        }


    }
}
