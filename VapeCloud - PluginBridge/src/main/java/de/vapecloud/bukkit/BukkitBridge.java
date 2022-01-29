package de.vapecloud.bukkit;

import de.vapecloud.bukkit.utils.GeneralHandler;
import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessIsReadyOutPacket;
import de.vapecloud.vapenet.VapeNetBootStrap;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitBridge extends JavaPlugin {


    private static BukkitBridge instance;
    private GeneralHandler generalHandler;

    @Override
    public void onEnable() {
        instance = this;
        new VapeDriver();
        this.generalHandler = new GeneralHandler();
        BukkitBridge.getInstance().getGeneralHandler().buildNetworking();

        ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
        CloudProcessIsReadyOutPacket packet = new CloudProcessIsReadyOutPacket();
        packet.setCurrentCluster(config.getRunningCluster());
        packet.setProcessName(config.getProcessName());
        packet.setPort(config.getProcessStartPort());
        packet.setProcessType(config.getProcessMode());
        VapeNetBootStrap.client.sendPacket(packet);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public GeneralHandler getGeneralHandler() {
        return generalHandler;
    }

    public static BukkitBridge getInstance() {
        return instance;
    }
}
