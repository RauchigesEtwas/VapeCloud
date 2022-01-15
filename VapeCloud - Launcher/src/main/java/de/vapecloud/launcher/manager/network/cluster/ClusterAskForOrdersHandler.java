package de.vapecloud.launcher.manager.network.cluster;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/11:56
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.networking.packets.cluster.out.ClusterAskForOrdersPacket;
import de.vapecloud.driver.networking.packets.cluster.out.ConfirmOrderPacket;
import de.vapecloud.driver.networking.packets.cluster.in.ClusterOrderQueuePacket;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;
import java.util.ArrayList;
import java.util.UUID;

public class ClusterAskForOrdersHandler extends PacketListener {



    @PacketProvideHandler(priority = 100)
    public void handelOrderRequest(PacketReceivedEvent event){

        Packet packet = event.getPacket();
        VapeNETChannel channel = event.getChannel();

        if (packet instanceof ClusterAskForOrdersPacket){
            ServiceConfig serviceConfig = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
            SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);
            ClusterAskForOrdersPacket orderPacket = (ClusterAskForOrdersPacket) packet;
            ArrayList<SubContainer> arry =  new ContainerHandler().getSubContainersFromCluster(orderPacket.getClusterName());
            for (int arryint = 0; arryint != arry.size() ; arryint++) {
                SubContainer container = arry.get(arryint);
                for (int start = 0; start != container.getTotalOnline(); start++) {
                    ClusterOrderQueuePacket queue = new ClusterOrderQueuePacket();
                    Integer id = VapeDriver.getInstance().getProcessHandler().getFreeID(container.getContainerName());
                    String processName = container.getContainerName() + serviceConfig.getInternalSplitter() + id;
                    queue.setSplitter(serviceConfig.getInternalSplitter());
                    queue.setProcessName(processName);
                    queue.setRunningCluster(container.getRunningCluster());
                    queue.setProcessVersion(container.getContainerVersion().toString());
                    queue.setProcessModeType(container.getContainerModeType().toString());
                    queue.setRunningPath("/live/" + container.getContainerName() + "/" + processName+ "~"+ UUID.randomUUID().toString() +"/");
                    queue.setSameAddress(channel.getLocalAddress().getHostAddress().equalsIgnoreCase(settingsConfig.getManagerAddresse()));
                    queue.setStaticProcess(container.getStaticService());
                    queue.setSelectedID(id);
                    queue.setMinimalMemory(container.getMinimalMemory());
                    queue.setMaximalMemory(container.getMaximalMemoery());
                    if (queue.getSameAddress()){
                        if (container.getContainerModeType().toString().equalsIgnoreCase("PROXY")){
                            queue.setProvideStartPort(VapeDriver.getInstance().getProcessHandler().getFreePort(serviceConfig.getProxyStartupPort()));
                        }else{
                            queue.setProvideStartPort(VapeDriver.getInstance().getProcessHandler().getFreePort(serviceConfig.getServerStartupPort()));
                        }
                    }else{
                        if (container.getContainerModeType().toString().equalsIgnoreCase("PROXY")){
                            queue.setProvideStartPort(serviceConfig.getProxyStartupPort());
                        }else{
                            queue.setProvideStartPort(serviceConfig.getServerStartupPort());
                        }
                    }
                    queue.setSubmitToQueue(false);
                    channel.sendPacket(queue);
                    VapeDriver.getInstance().getProcessHandler().addProcessToCluster(queue.getRunningCluster(), processName);
                }
            }
            ClusterOrderQueuePacket queue = new ClusterOrderQueuePacket();
            queue.setSubmitToQueue(true);
            channel.sendPacket(queue);
        }

    }

    @PacketProvideHandler(priority = 100)
    public void handleStartUPCallBack(PacketReceivedEvent event){
        Packet packet = event.getPacket();
        if (packet instanceof ConfirmOrderPacket){
            ConfirmOrderPacket confirmOrderPacket = (ConfirmOrderPacket) packet;
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The Process §e"+confirmOrderPacket.getProcessName()+"§7 is §astarting§7! [§e"+
                    confirmOrderPacket.getCluster()+"§7 | §e"+confirmOrderPacket.getProcessType()+"§7~§e"+confirmOrderPacket.getPort()+"§7@§e"+confirmOrderPacket.getVersion()+"§7]");

        }
    }
}
