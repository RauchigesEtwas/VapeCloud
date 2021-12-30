package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.codec.PacketDecoder;
import de.vapecloud.driver.networking.codec.PacketEntcoder;
import de.vapecloud.vapenet.VapeNETClient;
import de.vapecloud.vapenet.VapeNETOption;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.PacketManager;


public class Client {

    private int port;
    private String host;

    public Client() {}


    public Client bind(String host, int port){
        this.host = host;
        this.port = port;
        return this;
    }

    public void create(){
        try {
            VapeNetBootStrap.packetManager = new PacketManager();
            VapeNetBootStrap.client = new VapeNETClient();
            VapeNetBootStrap.client.init(channel -> {
                ChannelPipeline pipeline = channel.getPipeline();
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new PacketEntcoder());

            }).option(VapeNETOption.TEST, 2).bind(this.host, this.port).connect();
        }catch (Exception e){

        }
    }
}