package de.vapecloud.launcher.cluster.network;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/13:51
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.networking.packets.cluster.from.ConfirmOrderPacket;
import de.vapecloud.driver.networking.packets.cluster.to.ClusterOrderQueuePacket;
import de.vapecloud.driver.process.RunningProcess;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.handlers.PacketListener;
import de.vapecloud.vapenet.protocol.Packet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;

public class ClusterOrderAwnserHandler extends PacketListener {

    @Override
    public void handlePacket(IChannel channel, Packet packet) {
        if (packet instanceof ClusterOrderQueuePacket){
            ClusterOrderQueuePacket queuePacket = (ClusterOrderQueuePacket) packet;
            if (!queuePacket.getSubmitToQueue()){
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "a new job would be added to queue [§e"+queuePacket.getProcessName()+"§7]");
                VapeDriver.getInstance().getVapeSettings().getQueue().getProcessQueue().add(queuePacket);
            }else{

                VapeDriver.getInstance().getVapeSettings().getQueue().getProcessQueue().forEach(order -> {
                    ProcessCore core = new ProcessCore();
                    core.setProcessName(order.getProcessName());
                    core.setRunningCluster(order.getRunningCluster());
                    core.setSplitter(order.getSplitter());
                    core.setProcessVersion(order.getProcessVersion());
                    core.setProcessModeType(order.getProcessModeType());
                    core.setRunningPath(order.getRunningPath());
                    core.setStaticProcess(order.getStaticProcess());
                    core.setSelectedID(order.getSelectedID());
                    core.setMinimalMemory(order.getMinimalMemory());
                    core.setMaximalMemory(order.getMaximalMemory());
                    if (order.getSameAddress()){
                        core.setProvideStartPort(order.getProvideStartPort());
                    }else {
                        core.setProvideStartPort(VapeDriver.getInstance().getProcessHandler().getFreePort(order.getProvideStartPort()));
                    }
                    try {
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "now §estarting §7a new §eprocess §7[Name: §e"+core.getProcessName()+"§7~§e"+InetAddress.getLocalHost().getHostAddress()+"§7:§e"+core.getProvideStartPort()+"§7]");
                    } catch (UnknownHostException e) {

                    }

                    VapeDriver.getInstance().getProcessHandler().addProcess(order.getProcessName().split(order.getSplitter())[0], core);
                    VapeDriver.getInstance().getProcessHandler().getProcess(order.getProcessName()).runProcess();
                    ConfirmOrderPacket confirmOrderPacket = new ConfirmOrderPacket();
                    confirmOrderPacket.setProcessName(core.getProcessName());
                    confirmOrderPacket.setCluster(core.getRunningCluster());
                    confirmOrderPacket.setProcessType(core.getProcessModeType());
                    confirmOrderPacket.setVersion(core.getProcessVersion());
                    confirmOrderPacket.setPort(core.getProvideStartPort());
                    channel.sendPacket(confirmOrderPacket);
                });
            }
        }
    }
}
