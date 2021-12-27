package de.vapecloud.launcher.manager.network;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/19:02
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.packets.AuthClientPacket;
import de.vapecloud.driver.networking.packets.AuthRequestPacket;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.handlers.IPacketHandler;
import de.vapecloud.vapenet.protocol.Packet;
import lombok.SneakyThrows;

import java.util.UUID;

public class ClientConnectHandler implements IPacketHandler {


    @Override
    public void handleConnected(IChannel channel) {
        String  key = UUID.randomUUID().toString();
        channel.sendPacket(new AuthRequestPacket(key));
    }

    @SneakyThrows
    @Override
    public void handlePacket(IChannel channel, Packet packet) {
        if (packet instanceof AuthClientPacket){
            AuthClientPacket auth = (AuthClientPacket) packet;
            if(auth.isClustered()){
                SettingsConfig settingsConfig = (SettingsConfig) new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
                if(auth.getClusterAuthKey().equalsIgnoreCase(settingsConfig.getInternalAuthKey())){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The cluster §e"+auth.getClientName()+"§7 is now successfully connected and can start services");
                }else{
                    channel.close();
                }
            }else{

            }
        }
    }

    @Override
    public void handleDisconnected(IChannel channel) {

    }

    @Override
    public void handleException(Exception exception) {
    }
}
