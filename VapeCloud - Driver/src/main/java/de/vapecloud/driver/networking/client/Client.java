package de.vapecloud.driver.networking.client;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETClient;
import de.vapecloud.vapenet.VapeNETOption;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.PacketListenerHandler;


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
            VapeNetBootStrap.packetListenerHandler = new PacketListenerHandler();
            VapeNetBootStrap.client = new VapeNETClient();
            VapeNetBootStrap.client.init(channel -> {
                ChannelPipeline pipeline = channel.getPipeline();

            }).option(VapeNETOption.BUFFER_SIZE, 2024).bind(this.host, this.port).connect();
        }catch (Exception e){

        }
    }
}
