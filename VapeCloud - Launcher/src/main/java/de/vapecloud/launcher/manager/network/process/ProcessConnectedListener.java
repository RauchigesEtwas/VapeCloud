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
import de.vapecloud.driver.networking.packets.cloudprocess.in.CloudProcessUpdateInPacket;
import de.vapecloud.driver.networking.packets.cloudprocess.out.CloudProcessIsReadyOutPacket;
import de.vapecloud.driver.process.bin.ServerData;
import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.handlers.bin.PacketProvideHandler;
import de.vapecloud.vapenet.handlers.listener.PacketReceivedEvent;
import de.vapecloud.vapenet.protocol.Packet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ProcessConnectedListener extends PacketListener {

    @PacketProvideHandler
    public void handleEvent(PacketReceivedEvent event){
        Packet packet = event.getPacket();
        VapeNETChannel channel = event.getChannel();
        if (packet instanceof CloudProcessIsReadyOutPacket){
            CloudProcessIsReadyOutPacket process = (CloudProcessIsReadyOutPacket) packet;
            if (VapeDriver.getInstance().getProcessHandler().getConnectedStatus(process.getProcessName())){
                return;
            }else {
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The process §e"+process.getProcessName()+"§7 is now §aconnected §7[§e"+process.getProcessType()+"§7~§e"+process.getProcessPort()+"§7]");

                VapeDriver.getInstance().getProcessHandler().changeConnectionStatusFromProcess(process.getProcessName());
                VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().registerChannel(process.getProcessName(), event.getChannel());
                if (process.getProcessType().equalsIgnoreCase("PROXY")){
                    Map<String, String> data = new HashMap<>();
                    VapeDriver.getInstance().getProcessHandler().getConnectedServers().forEach(serverData -> {
                        CloudProcessUpdateInPacket update = new CloudProcessUpdateInPacket();
                        data.clear();
                        data.put("updatetype", "ADDPROCESS");
                        data.put("processname", serverData.getProcessName());
                        data.put("processhost", serverData.getHostAddress());
                        data.put("processport", String.valueOf(serverData.getPort()));
                        data.put("processtype", serverData.getProcessMode());
                        update.setData(data);
                        channel.sendPacket(update);
                    });
                }else {
                    VapeDriver.getInstance().getProcessHandler().getConnectedServers().add(new ServerData(channel.getLocalAddress().getHostAddress(), process.getProcessName(),process.getProcessType(), process.getProcessPort()));
                    CloudProcessUpdateInPacket update = new CloudProcessUpdateInPacket();
                    Map<String, String> data = new HashMap<>();
                    data.put("updatetype", "ADDPROCESS");
                    data.put("processname", process.getProcessName());
                    data.put("processhost", channel.getLocalAddress().getHostAddress());
                    data.put("processport", String.valueOf(process.getProcessPort()));
                    data.put("processtype", process.getProcessType());

                    update.setData(data);
                    ContainerHandler container =  new ContainerHandler();

                    container.getSubContainersNames().forEach(scon -> {
                        if (container.getSubContainers(scon).getContainerModeType().toString().equalsIgnoreCase("PROXY")){
                            VapeDriver.getInstance().getNetworkHandler().getConnectionHandler().getChannelsByGroup(scon).forEach((s, channel1) -> channel1.sendPacket(update));
                        }
                    });
                }
            }
        }
    }
}
