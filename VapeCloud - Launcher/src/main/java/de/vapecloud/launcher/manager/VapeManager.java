package de.vapecloud.launcher.manager;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.networking.packets.cluster.in.ShutdownAllPacket;
import de.vapecloud.driver.networking.server.Server;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.driver.restapi.RestAPIServer;
import de.vapecloud.launcher.manager.commands.*;
import de.vapecloud.launcher.manager.network.cluster.ClusterAskForOrdersHandler;
import de.vapecloud.launcher.manager.network.cluster.ClusterAuthHandler;
import de.vapecloud.launcher.manager.network.process.ProcessConnectedListener;
import de.vapecloud.vapenet.VapeNetBootStrap;

import java.util.UUID;

public class VapeManager {

    public void initVapeManager(){

        //BUILD NETWORKING
        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the network is §eprepared §7& §ebind §7to connect on §e" + settingsConfig.getInternalPort());
        registerNetworking(settingsConfig);




        ContainerHandler containerHandler = new ContainerHandler();
        StringBuilder aliases = new StringBuilder();
        for (int i = 0; i !=   containerHandler.getSubContainersNames().size(); i++){
            if(i == 0){
                aliases = new StringBuilder("§e" + containerHandler.getSubContainersNames().get(i));
            }else{
                aliases.append("§7, §e").append(containerHandler.getSubContainersNames().get(i));
            }
        }
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "All §econtainers§7 were found and §aloadeds§7 [" + aliases + "§7]");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the restapi is §eprepared §7& §ebind §7to connect on §e9087");
        new RestAPIServer().bind(9087).create();
        //LOAD MODULES
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "the modules are §eprepared §7and can be §eload");
        VapeDriver.getInstance().getModuleHandler().loadModules();
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "all modules were found and were §aloaded §7[§e"+  VapeDriver.getInstance().getModuleHandler().getLoadedModules().size() + " Module§7]");
        registerCommands();


        long time =       VapeDriver.getInstance().getVapeSettings().getStartCount();
        long finalTime =  (System.currentTimeMillis() - time);
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the cloud is §enow ready§7 to use [It takes §e"+finalTime+" ms§r]");
        ServiceConfig serviceConfig = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "a new §ecluster §7wants to §everify itself §7[§eInternalCluster§7~§e127.0.0.1§7@§e"+settingsConfig.getInternalPort()+"§7]");
        VapeDriver.getInstance().getProcessHandler().addConnectedCluster("InternalCluster");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "a new §eCluster §7was §aregistered §7[§eInternalCluster§7~§e127.0.0.1§7@§e"+settingsConfig.getInternalPort()+"§7]");
        shutdownHook();


        for (int i = 0; i != containerHandler.getSubContainersFromCluster("InternalCluster").size(); i++) {
            for (int is = 0; is !=   containerHandler.getSubContainersFromCluster("InternalCluster").get(i).getTotalOnline(); is++){
                runInternalProcess(containerHandler.getSubContainersFromCluster("InternalCluster").get(i), serviceConfig);
            }
        }
        while (true){
        }

    }


    private void registerNetworking(SettingsConfig settingsConfig){
        new VapeNetBootStrap();
        VapeDriver.getInstance().getNetworkHandler().server = new Server();
        VapeDriver.getInstance().getNetworkHandler().server.bind(settingsConfig.getInternalPort()).create();
        VapeNetBootStrap.packetListenerHandler.registerListener(new ClusterAuthHandler());
        VapeNetBootStrap.packetListenerHandler.registerListener(new ClusterAskForOrdersHandler());
        VapeNetBootStrap.packetListenerHandler.registerListener(new ProcessConnectedListener());

    }

    private void runInternalProcess(SubContainer container,   ServiceConfig serviceConfig){
        Integer id = VapeDriver.getInstance().getProcessHandler().getFreeID(container.getContainerName());
        String processName = container.getContainerName() + serviceConfig.getInternalSplitter() + id;
        VapeDriver.getInstance().getProcessHandler().addProcessToCluster("InternalCluster", processName);
            ProcessCore core = new ProcessCore();
            core.setProcessName(processName);
            core.setRunningCluster("InternalCluster");
            core.setStaticProcess(container.getStaticService());
            core.setProcessVersion(container.getContainerVersion().toString());
            core.setProcessModeType(container.getContainerModeType().toString());
            core.setSplitter(serviceConfig.getInternalSplitter());
            core.setRunningPath("/live/" + container.getContainerName() + "/" + processName + "~"+ UUID.randomUUID() +"/");
            if (container.getContainerModeType().toString().equalsIgnoreCase("PROXY")){
                core.setProvideStartPort(VapeDriver.getInstance().getProcessHandler().getFreePort(serviceConfig.getProxyStartupPort()));
            }else{
                core.setProvideStartPort(VapeDriver.getInstance().getProcessHandler().getFreePort(serviceConfig.getServerStartupPort()));
            }
            core.setSelectedID(id);
            core.setMinimalMemory(container.getMinimalMemory());
            core.setMaximalMemory(container.getMaximalMemoery());
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The Process §e"+core.getProcessName()+"§7 is §astarting§7! [§e"+
                core.getRunningCluster()+"§7 | §e"+core.getProcessModeType()+"§7~§e"+core.getProvideStartPort()+"§7@§e"+core.getProcessVersion()+"§7]");
            VapeDriver.getInstance().getProcessHandler().addProcess(container.getContainerName(), core);
            VapeDriver.getInstance().getProcessHandler().getProcess(processName).runProcess();

    }


    private void shutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "the cloud is now §eshutting down");
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "thank you for use §eVapeCloud");
            VapeDriver.getInstance().getProcessHandler().getProcessFromCluster("InternalCluster").forEach((process, connected) -> VapeDriver.getInstance().getProcessHandler().getProcess(process).stopProcess());
            VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().getChannels().forEach((s, iChannel) -> iChannel.sendPacket(new ShutdownAllPacket()));
            VapeNetBootStrap.server.close();
        }));
    }


    private void registerCommands(){

        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new HelpCommand("help", "here you will find all commands", "?", "ls", "ask"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ClearConsolCommand("clear", "clean the console of the cloud", "cls"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ExitCommand("exit", "Shut down the cloud completely", "shutdown", "kill", "quit", "end", "stop"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ContainerCommand("container", "manage your sub and main containers", "group", "g", "con"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ProcessCommand("process", "manage all processes on the cloud", "ps", "run"));
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().registerCommand(new ReloadCommand("reload", "reload all Modules and Configs", "rl"));
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "all §ecommands were §aloaded §7& §aregister §7[§e"+ VapeDriver.getInstance().getConsolHandler().getCommandHandler().getCommands().size()+" Commands§7]");
    }

}
