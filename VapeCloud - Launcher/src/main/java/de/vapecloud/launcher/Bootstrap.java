package de.vapecloud.launcher;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.utils.setup.SetupTypes;
import de.vapecloud.launcher.cluster.VapeCluster;
import de.vapecloud.launcher.manager.VapeManager;
import lombok.SneakyThrows;

import java.util.HashMap;


/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class Bootstrap {


    @SneakyThrows
    public static void main(String[] args) {
        new VapeDriver();
        if (VapeDriver.getInstance().getConsolHandler() == null){
            VapeDriver.getInstance().setConsolHandler(new ConsolHandler());
            VapeDriver.getInstance().getConsolHandler().createHandel("CONSOLE");
        }
        VapeDriver.getInstance().getConsolHandler().clearScreen();
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.EMPTY,false, VapeDriver.getInstance().getVapeSettings().getDataCenter().getLogo());

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,false, "New instance of the VapeCloud will be launched.....");
        Thread.sleep(50);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,false, "we look if the cloud is already setup....");
        Thread.sleep(50);
        if(!new ConfigHandler("./settings.json").canFinde()){
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,false, "Oh no the cloud still needs to be setup§7.");
            Thread.sleep(50);
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,false, "A small setup will now be executed");

            VapeDriver.getInstance().getVapeSettings().getSetupData().setupTypes = SetupTypes.STARTUP;
            VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = true;
            VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory = new HashMap<>();
            VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,false, "what do you want to setup?");
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,false, "Options: Manager / Cluster");
            while (true){}
        }else{
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,false, "The cloud is ready for takeoff");

          SettingsConfig settingsConfig = (SettingsConfig) new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);

            if(settingsConfig.getRunningInstance().equalsIgnoreCase("manager")){
                new VapeManager().initVapeManager();
            }else {
                new VapeCluster().initVapeCluster();
            }

        }
    }


}
