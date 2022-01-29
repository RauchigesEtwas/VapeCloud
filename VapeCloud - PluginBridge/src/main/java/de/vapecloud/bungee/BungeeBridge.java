package de.vapecloud.bungee;

import de.vapecloud.bungee.commands.CloudCommand;
import de.vapecloud.bungee.listener.CloudPlayerEnterNetworkListener;
import de.vapecloud.bungee.network.UpdatePacketHandler;
import de.vapecloud.bungee.utils.GeneralHandler;
import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.client.Client;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessIsReadyOutPacket;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.PacketListenerHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeBridge extends Plugin {


    private static BungeeBridge instance;
    private GeneralHandler generalHandler;
    private PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();

    @Override
    public void onEnable() {
        instance = this;
        this.generalHandler = new GeneralHandler();
        new VapeDriver();

        ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);

        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(config.getManagerAddress(), config.getPort()).create();
        VapeNetBootStrap.packetListenerHandler.registerListener(new UpdatePacketHandler());
        CloudProcessIsReadyOutPacket packet = new CloudProcessIsReadyOutPacket();
        packet.setCurrentCluster(config.getRunningCluster());
        packet.setProcessName(config.getProcessName());
        packet.setPort(config.getProcessStartPort());
        packet.setProcessType(config.getProcessMode());
        VapeNetBootStrap.client.sendPacket(packet);
        pluginManager.registerCommand(this, new CloudCommand("cloud"));
        pluginManager.registerCommand(this, new CloudCommand("vapecloud"));
        pluginManager.registerListener(this, new CloudPlayerEnterNetworkListener());

    }

    @Override
    public void onDisable() {
        instance = null;
    }
    public GeneralHandler getGeneralHandler() {
        return generalHandler;
    }
    public static BungeeBridge getInstance() {
        return instance;
    }
}
