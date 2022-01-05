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
import de.vapecloud.driver.networking.packets.cluster.from.ClusterAskForOrdersPacket;
import de.vapecloud.driver.networking.packets.cluster.from.ConfirmOrderPacket;
import de.vapecloud.driver.networking.packets.cluster.to.ClusterOrderQueuePacket;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.handlers.PacketListener;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.ArrayList;

public class ClusterAskForOrdersHandler extends PacketListener {

    @Override
    public void handlePacket(IChannel channel, Packet packet) {
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
                    if(container.getStaticService()){
                        queue.setRunningPath("/live/static/" + container.getContainerName() + "/" + processName+ "/");
                    }else {
                        queue.setRunningPath("/live/dynamic/" + container.getContainerName() + "/" + processName+ "/");
                    }
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
                }
            }
            ClusterOrderQueuePacket queue = new ClusterOrderQueuePacket();
            queue.setSubmitToQueue(true);
            channel.sendPacket(queue);
        }

        if (packet instanceof ConfirmOrderPacket){
            ConfirmOrderPacket confirmOrderPacket = (ConfirmOrderPacket) packet;
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The Process §e"+confirmOrderPacket.getProcessName()+"§7 is being §astarted§7! [Cluster: §e"+
                    confirmOrderPacket.getCluster()+"§7, Data: §e"+confirmOrderPacket.getProcessType()+"§7~§e"+confirmOrderPacket.getPort()+"§7@§e"+confirmOrderPacket.getVersion()+"§7]");
        }
    }
}
