package de.vapecloud.launcher.cluster.network;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/19:00
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.packets.AuthClientPacket;
import de.vapecloud.driver.networking.packets.AuthRequestPacket;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.handlers.PacketListener;
import de.vapecloud.vapenet.protocol.Packet;

public class AuthRequestHandler extends PacketListener {

    @Override
    public void handleConnected(IChannel channel) {

    }

    @Override
    public void handlePacket(IChannel channel, Packet packet) {
        if(packet instanceof AuthRequestPacket){
            AuthRequestPacket auth = (AuthRequestPacket) packet;
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "A request was received from the manager that the cluster needs to authenticate itself");
            AuthClientPacket clientPacket = new AuthClientPacket();
            SettingsConfig settingsConfig = (SettingsConfig) new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
            clientPacket.setClientName(settingsConfig.getRunningInstance());
            clientPacket.setKey(auth.getKey());
            clientPacket.setClustered(true);
            clientPacket.setClusterAuthKey(settingsConfig.getInternalAuthKey());

            channel.sendPacket(clientPacket);
        }
    }

    @Override
    public void handleDisconnected(IChannel channel) {

    }

    @Override
    public void handleException(Exception exception) {

    }
}
