package de.vapecloud.driver.networking.server;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETServer;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.PacketListenerHandler;

public class Server {


    private int port;

    public Server() {}



    public Server bind(int port){
        this.port = port;
        return this;
    }


    public void create(){
        try {
            VapeNetBootStrap.packetListenerHandler = new PacketListenerHandler();
            VapeNetBootStrap.server = new VapeNETServer();
            VapeNetBootStrap.server.init(channel -> {
                ChannelPipeline pipeline = channel.getPipeline();
            }).bind(this.port);
        }catch (Exception e){

        }
    }
}
