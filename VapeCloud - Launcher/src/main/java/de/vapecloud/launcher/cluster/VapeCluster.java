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
import de.vapecloud.driver.networking.Client;
import de.vapecloud.launcher.cluster.network.AuthRequestHandler;
import de.vapecloud.launcher.manager.network.ClientConnectHandler;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;

public class VapeCluster {

    public void initVapeCluster(){


        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);


        VapeDriver.getInstance().getVapeSettings().setRunningaCluster(true);

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "Try to connect with the manager (Address: §e"+settingsConfig.getManagerAddresse()+"§7 / Port: §e"+settingsConfig.getInternalPort()+"");
        registerNetworking(settingsConfig);
        while (true){}
    }

    private void registerNetworking(SettingsConfig settingsConfig){
        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(settingsConfig.getManagerAddresse(), settingsConfig.getInternalPort()).create();
        VapeNetBootStrap.getInstance().packetManager.addPacketHandler(new AuthRequestHandler());


    }
}
