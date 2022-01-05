package de.vapecloud.launcher.cluster.network;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/11:03
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.packets.cluster.from.ClusterAskForOrdersPacket;
import de.vapecloud.driver.networking.packets.cluster.to.AuthClusterAwnserPacket;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.handlers.PacketListener;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthAwnserHandler extends PacketListener {

    @Override
    public void handlePacket(IChannel channel, Packet packet) {
        if (packet instanceof AuthClusterAwnserPacket){
            AuthClusterAwnserPacket resuls = (AuthClusterAwnserPacket) packet;
            if (resuls.isConnectionAccept()){
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "the connection was successful, they are waiting for orders....");
                SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
                VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().connectionToManager = channel;
                ClusterAskForOrdersPacket cafoPacket = new ClusterAskForOrdersPacket();
                cafoPacket.setClusterName(settingsConfig.getRunningInstance());
                channel.sendPacket(cafoPacket);
            }else{
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "the connection was §erejected§7, this §einstance§7 will be §eshutdown §7shortly");
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "reason: §e" + resuls.getReason());

            }
        }
    }
}
