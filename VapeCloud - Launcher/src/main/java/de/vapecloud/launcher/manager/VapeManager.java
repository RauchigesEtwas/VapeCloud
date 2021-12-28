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
import de.vapecloud.driver.networking.Server;
import de.vapecloud.launcher.manager.commands.ClearConsolCommand;
import de.vapecloud.launcher.manager.commands.ExitCommand;
import de.vapecloud.launcher.manager.commands.HelpCommand;
import de.vapecloud.launcher.manager.network.ClientConnectHandler;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.IChannelInitializer;

public class VapeManager {

    public void initVapeManager(){


        //BUILD NETWORKING
        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "try to bind the network on port §e" + settingsConfig.getInternalPort());
        registerNetworking(settingsConfig);

        registerCommands();

        while (true){}

    }


    private void registerNetworking(SettingsConfig settingsConfig){

        VapeDriver.getInstance().getNetworkHandler().server = new Server();
        VapeDriver.getInstance().getNetworkHandler().server.bind(settingsConfig.getInternalPort()).create();
        VapeNetBootStrap.getInstance().packetManager.addPacketHandler(new ClientConnectHandler());
    }

    private void registerCommands(){
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new HelpCommand("help", "here you will find all commands", "?", "ls", "ask"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ClearConsolCommand("clear", "clean the console of the cloud", "cls"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ExitCommand("exit", "Shut down the cloud completely", "shutdown", "kill", "quit", "end"));

    }

}
