package de.vapecloud.driver.networking.server;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.bin.PacketDecoder;
import de.vapecloud.driver.networking.bin.PacketEntcoder;
import de.vapecloud.vapenet.VapeNETServer;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.PacketListener;
import de.vapecloud.vapenet.handlers.PacketManager;

public class Server {


    private int port;

    public Server() {

    }



    public Server bind(int port){
        this.port = port;
        return this;
    }


    public void create(){
        try {
            VapeNetBootStrap.packetManager = new PacketManager();
            VapeNetBootStrap.server = new VapeNETServer();
            VapeNetBootStrap.server.init(channel -> {
                ChannelPipeline pipeline = channel.getPipeline();
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new PacketEntcoder());
            }).bind(this.port);
        }catch (Exception e){

        }
    }
}