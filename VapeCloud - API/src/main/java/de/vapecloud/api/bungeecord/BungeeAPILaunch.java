package de.vapecloud.api.bungeecord;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:55
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.api.bungeecord.handlers.CloudPlayerEnterNetworkListener;
import de.vapecloud.api.bungeecord.network.AddProcessListener;
import de.vapecloud.api.bungeecord.network.UpdateProcessListener;
import de.vapecloud.api.bungeecord.utils.serverhelper.ServerHelper;
import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.client.Client;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessConnecedPacket;
import de.vapecloud.vapenet.VapeNetBootStrap;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.ArrayList;

public class BungeeAPILaunch extends Plugin {

    private static BungeeAPILaunch instance;
    public ArrayList<String> Lobbys;
    public PluginManager pluginManager;


    @Override
    public void onEnable() {
        new VapeDriver();
        new CloudAPI();
        instance = this;
        this.Lobbys = new ArrayList<>();
        pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerListener(this, new CloudPlayerEnterNetworkListener());
        ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(config.getManagerAddress(), config.getPort());
        VapeDriver.getInstance().getNetworkHandler().client.create();
        VapeNetBootStrap.packetListenerHandler.registerListener(new AddProcessListener());
        VapeNetBootStrap.packetListenerHandler.registerListener(new UpdateProcessListener());

        ServerHelper.removeServer("lobby");
        CloudProcessConnecedPacket packet = new CloudProcessConnecedPacket();
        packet.setProcessName(config.getProcessName());
        packet.setProcessType(config.getProcessMode());
        packet.setRunningCluster(config.getRunningCluster());
        packet.setPort(config.getProcessStartPort());
        VapeNetBootStrap.client.sendPacket(packet);


    }

    @Override
    public void onDisable() {
        
    }

    public static BungeeAPILaunch getInstance() {
        return instance;
    }
}
