package de.vapecloud.launcher.manager.network.process;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/13:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.networking.packets.cloudprocess.in.ProcessBindeToBungeePacket;
import de.vapecloud.driver.networking.packets.cloudprocess.in.ProcessUpdatePacket;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessConnecedPacket;
import de.vapecloud.driver.process.bin.ServerData;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ProcessConnectedListener extends PacketListener {

    @PacketProvideHandler(priority = 100)
    public void handleProcessConnection(PacketReceivedEvent event){
        Packet packet2 = event.getPacket();
        VapeNETChannel channel = event.getChannel();
        if (packet2 instanceof CloudProcessConnecedPacket){
            CloudProcessConnecedPacket packet = (CloudProcessConnecedPacket) event.getPacket();


            if (VapeDriver.getInstance().getProcessHandler().getConnectedStatus(packet.getProcessName())){
                 return;
            }else {
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The process §e"+packet.getProcessName()+"§7 is now §aconnected §7[§e"+packet.getProcessType()+"§7~§e"+packet.getPort()+"§7]");

                VapeDriver.getInstance().getProcessHandler().changeConnectionStatusFromProcess(packet.getProcessName());
                VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().registerChannel(packet.getProcessName(), event.getChannel());

                ProcessUpdatePacket updatePacket = new ProcessUpdatePacket();
                updatePacket.setJsonText(new ConfigHandler("").convertToJson(new ConfigHandler("./service.json").getConfig(ServiceConfig.class)));
                updatePacket.setFileName("serviceconfig");
                channel.sendPacket(updatePacket);

                if (packet.getProcessType().equalsIgnoreCase("PROXY")){
                    VapeDriver.getInstance().getProcessHandler().getConnectedServers().forEach(serverData -> {
                        ProcessBindeToBungeePacket bungeePacket = new ProcessBindeToBungeePacket();
                        bungeePacket.setProcessName(serverData.getProcessName());
                        bungeePacket.setHostAddress(serverData.getHostAddress());
                        bungeePacket.setProcessMode(serverData.getProcessMode());
                        bungeePacket.setPort(serverData.getPort());
                        channel.sendPacket(bungeePacket);
                    });
                } else {
                    VapeDriver.getInstance().getProcessHandler().getConnectedServers().add(new ServerData(channel.getLocalAddress().getHostAddress(), packet.getProcessName(),packet.getProcessType(), packet.getPort()));
                    ProcessBindeToBungeePacket bungeePacket = new ProcessBindeToBungeePacket();
                    bungeePacket.setProcessName(packet.getProcessName());
                    bungeePacket.setHostAddress(channel.getLocalAddress().getHostAddress());
                    bungeePacket.setProcessMode(packet.getProcessType());
                    bungeePacket.setPort(packet.getPort());
                    ContainerHandler container =  new ContainerHandler();

                    container.getSubContainersNames().forEach(scon -> {
                        if (container.getSubContainers(scon).getContainerModeType().toString().equalsIgnoreCase("PROXY")){
                            VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().getChannelsByGroup(scon).forEach(new BiConsumer<String, IChannel>() {
                                @Override
                                public void accept(String s, IChannel channel) {
                                    channel.sendPacket(bungeePacket);
                                }
                            });
                        }

                    });

                }
            }
        }
    }
}
