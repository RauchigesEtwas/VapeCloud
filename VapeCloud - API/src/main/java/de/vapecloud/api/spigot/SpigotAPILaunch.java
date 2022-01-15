package de.vapecloud.api.spigot;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:57
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.client.Client;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessConnecedPacket;
import de.vapecloud.vapenet.VapeNetBootStrap;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotAPILaunch extends JavaPlugin {

    @Override
    public void onEnable() {
        new VapeDriver();
        new CloudAPI();
        ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(config.getManagerAddress(), config.getPort());
        VapeDriver.getInstance().getNetworkHandler().client.create();
        CloudProcessConnecedPacket packet = new CloudProcessConnecedPacket();
        packet.setProcessName(config.getProcessName());
        packet.setPort(config.getProcessStartPort());
        packet.setProcessType(config.getProcessMode());
        packet.setRunningCluster(config.getRunningCluster());
        VapeNetBootStrap.client.sendPacket(packet);
    }

    @Override
    public void onDisable() {
        
    }
}
