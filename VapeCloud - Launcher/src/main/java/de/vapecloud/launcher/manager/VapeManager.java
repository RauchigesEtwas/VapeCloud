package de.vapecloud.launcher.manager;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.containers.MainContainer;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.container.enums.ContainerModeType;
import de.vapecloud.driver.container.enums.ContainerVersion;
import de.vapecloud.driver.networking.Server;
import de.vapecloud.launcher.manager.commands.*;
import de.vapecloud.launcher.manager.network.ClientConnectHandler;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;
import sun.applet.Main;

import java.util.function.Consumer;

public class VapeManager {

    public void initVapeManager(){


        //BUILD NETWORKING
        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "try to bind the network on port §e" + settingsConfig.getInternalPort());
        registerNetworking(settingsConfig);

        //LOAD MODULES
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "try §eloading §7the §emodules§7...");
        VapeDriver.getInstance().getModuleHandler().loadModules();
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "Loaded §e"+  VapeDriver.getInstance().getModuleHandler().getLoadedModules().size() + " §7Module");
        registerCommands();

        long time =       VapeDriver.getInstance().getVapeSettings().getStartCount();
        long finalTime =  (System.currentTimeMillis() - time);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the cloud is §enow ready§7 for use (§e"+finalTime+" ms§r)");
        ContainerHandler containerHandler = new ContainerHandler();
        containerHandler.getSubContainersFromCluster("InternalCluster").forEach(subContainer -> {
            //TODO: START PROCESSES BY INTERNALCLUSTER
        });
        while (true){}

    }


    private void registerNetworking(SettingsConfig settingsConfig){
        new VapeNetBootStrap();
        VapeDriver.getInstance().getNetworkHandler().server = new Server();
        VapeDriver.getInstance().getNetworkHandler().server.bind(settingsConfig.getInternalPort()).create();
        VapeNetBootStrap.packetManager.addPacketHandler(new ClientConnectHandler());
    }

    private void registerCommands(){

        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new HelpCommand("help", "here you will find all commands", "?", "ls", "ask"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ClearConsolCommand("clear", "clean the console of the cloud", "cls"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ExitCommand("exit", "Shut down the cloud completely", "shutdown", "kill", "quit", "end", "stop"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ContainerCommand("container", "manage your sub and main containers", "group", "g", "con"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ProcessCommand("process", "manage all processes on the cloud", "ps", "run"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ReloadCommand("reload", "reload all Modules and Configs", "rl"));
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "all commands were loaded (§e"+ VapeDriver.getInstance().getConsolHandler().getCommandHandler().getCommands().size()+" Commands§7)");
    }

}
