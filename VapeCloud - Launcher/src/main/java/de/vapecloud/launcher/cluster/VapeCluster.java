package de.vapecloud.launcher.cluster;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.client.Client;
import de.vapecloud.driver.networking.packets.cluster.out.AuthClusterPacket;
import de.vapecloud.launcher.cluster.network.AuthAwnserHandler;
import de.vapecloud.launcher.cluster.network.ClusterOrderAwnserHandler;
import de.vapecloud.launcher.cluster.network.ShutdownAllPacketHandler;
import de.vapecloud.vapenet.VapeNetBootStrap;

public class VapeCluster {

    public void initVapeCluster(){


        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);


        VapeDriver.getInstance().getVapeSettings().setRunningaCluster(true);
        VapeDriver.getInstance().getProcessHandler().addConnectedCluster(settingsConfig.getRunningInstance());

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "Try to connect with the manager [Address: §e"+settingsConfig.getManagerAddresse()+"§7 / Port: §e"+settingsConfig.getInternalPort()+"§7]");
        registerNetworking(settingsConfig);

        long time =       VapeDriver.getInstance().getVapeSettings().getStartCount();
        long finalTime =  (System.currentTimeMillis() - time);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the cloud is now ready for use [§e"+finalTime+" ms§r]");
        shutdownHook();
        AuthClusterPacket packet = new AuthClusterPacket();
        packet.setClusterName(settingsConfig.getRunningInstance());
        packet.setClusterAuthentorKey(settingsConfig.getInternalAuthKey());
        VapeNetBootStrap.client.sendPacket(packet);
        while (true){}
    }

    private void registerNetworking(SettingsConfig settingsConfig){
        new VapeNetBootStrap();
        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(settingsConfig.getManagerAddresse(), settingsConfig.getInternalPort()).create();
        VapeNetBootStrap.packetListenerHandler.registerListener(new AuthAwnserHandler());
        VapeNetBootStrap.packetListenerHandler.registerListener(new ClusterOrderAwnserHandler());
        VapeNetBootStrap.packetListenerHandler.registerListener(new ShutdownAllPacketHandler());
    }


    private void shutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the cloud is now §eshutting down");
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "thank you for use §eVapeCloud");
            SettingsConfig settingsConfig = (SettingsConfig) new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);

            VapeDriver.getInstance().getProcessHandler().getProcessFromCluster(settingsConfig.getRunningInstance()).forEach((process, aBoolean) -> VapeDriver.getInstance().getProcessHandler().getProcess(process).stopProcess());
        }));
    }
}
