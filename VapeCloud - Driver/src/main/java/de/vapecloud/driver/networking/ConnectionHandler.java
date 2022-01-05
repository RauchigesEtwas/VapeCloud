package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 05.01.2022/10:41
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.vapenet.channel.IChannel;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class ConnectionHandler {

    private HashMap<String, HashMap<String, IChannel>> channelsByGroups;
    private HashMap<String, IChannel> channelsByClusterAndModule;
    public IChannel connectionToManager;

    public ConnectionHandler() {
        channelsByGroups = new HashMap<>();
        channelsByClusterAndModule = new HashMap<>();
    }

    public HashMap<String, IChannel> getChannelsByGroup(String group){
        if (!this.channelsByGroups.containsKey(group))
            this.channelsByGroups.put(group, new HashMap<>());

        return this.channelsByGroups.get(group);
    }

    public HashMap<String, IChannel> getChannels(){
        return channelsByClusterAndModule;
    }

    public void sendPacketToClusters(Packet packet){
        channelsByClusterAndModule.forEach((cluster, channel) -> {
            channel.sendPacket(packet);
        });
    }

    public void sendPacketToGroup(String group, Packet packet){
        if (this.channelsByGroups.containsKey(group)){
            this.channelsByGroups.get(group).forEach((process, channel) -> channel.sendPacket(packet));
        }
    }

    public void unregisterChannel(String channelName){
        ServiceConfig config = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
        if (new ContainerHandler().getSubContainersNames().contains(channelName.split(config.getInternalSplitter())[0])){
            String group = channelName.split(config.getInternalSplitter())[0];
            if (this.channelsByGroups.containsKey(group) && this.channelsByGroups.get(group).containsKey(channelName)){
                this.channelsByGroups.get(group).remove(channelName);
            }
        }else {
            if (this.channelsByClusterAndModule.containsKey(channelName))
                this.channelsByClusterAndModule.remove(channelName);
        }
    }

    public void registerChannel(String channelName, IChannel channel){
        ServiceConfig config = (ServiceConfig)new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
        if (new ContainerHandler().getSubContainersNames().contains(channelName.split(config.getInternalSplitter())[0])){
            String group = channelName.split(config.getInternalSplitter())[0];
                if (!this.channelsByGroups.containsKey(group))
                    this.channelsByGroups.put(group, new HashMap<>());

                if (!this.channelsByGroups.get(group).containsKey(channelName)){
                    this.channelsByGroups.get(group).put(channelName, channel);
                }
        }else{
            if (!this.channelsByClusterAndModule.containsKey(channelName)){
                this.channelsByClusterAndModule.put(channelName, channel);
            }
        }
    }

}
