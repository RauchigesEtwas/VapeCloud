package de.vapecloud.launcher.manager.network.cluster;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/11:15
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.packets.cluster.out.AuthClusterPacket;
import de.vapecloud.driver.networking.packets.cluster.in.AuthClusterAwnserPacket;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;

public class ClusterAuthHandler extends PacketListener {

    @PacketProvideHandler(priority = 100)
    public void handleAuth(PacketReceivedEvent event){
        
        Packet packet = event.getPacket();
        VapeNETChannel channel = event.getChannel();

        if (packet instanceof AuthClusterPacket){
            SettingsConfig config = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
            ServiceConfig serviceConfig = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
            AuthClusterPacket resuls = (AuthClusterPacket) packet;
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "New §ecluster §7wants to §everify itself §7[§e"+resuls.getClusterName()+"§7~§e"+channel.getLocalAddress().getHostAddress()+"§7@§e"+config.getInternalPort()+"§7]");

            if (!VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().getChannels().containsKey(resuls.getClusterName())){
                if (config.getInternalAuthKey().contains(resuls.getClusterAuthentorKey())){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "a new §eCluster §7was §aregistered §7[§e"+resuls.getClusterName()+"§7~§e"+channel.getLocalAddress().getHostAddress()+"§7@§e"+config.getInternalPort()+"§7]");
                    AuthClusterAwnserPacket awnserPacket = new AuthClusterAwnserPacket();
                    VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().registerChannel(resuls.getClusterName(), channel);
                    awnserPacket.setConnectionAccept(true);
                    channel.sendPacket(awnserPacket);
                    return;
                }else {
                    AuthClusterAwnserPacket awnserPacket = new AuthClusterAwnserPacket();
                    awnserPacket.setConnectionAccept(false);
                    awnserPacket.setReason("The authentication key is incorrect");
                    channel.sendPacket(awnserPacket);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORKERROR, false, "the §everification has §efailed§7 [§c"+awnserPacket.getReason()+"§7]");
                    return;
                }
            }else {
                AuthClusterAwnserPacket awnserPacket = new AuthClusterAwnserPacket();
                awnserPacket.setConnectionAccept(false);
                awnserPacket.setReason("the cluster is already connected");
                channel.sendPacket(awnserPacket);
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORKERROR, false, "the §everification has §efailed§7 [§c"+awnserPacket.getReason()+"§7]");
                return;
            }
        }
    }

}
