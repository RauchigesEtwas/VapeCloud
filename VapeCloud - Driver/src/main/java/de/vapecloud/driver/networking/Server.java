package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.codec.PacketDecoder;
import de.vapecloud.driver.networking.codec.PacketEntcoder;
import de.vapecloud.vapenet.VapeNETServer;
import de.vapecloud.vapenet.channel.ChannelPipeline;
import de.vapecloud.vapenet.VapeNetBootStrap;
import lombok.SneakyThrows;

public class Server {


    private int port;

    public Server() {

    }



    public Server bind(int port){
        this.port = port;
        return this;
    }


    @SneakyThrows
    public void create(){
        new VapeNetBootStrap();
        VapeNetBootStrap.getInstance().server = new VapeNETServer();
        VapeNetBootStrap.getInstance().server.init(channel -> {
            ChannelPipeline pipeline = channel.getPipeline();
            pipeline.codec(new PacketDecoder());
            pipeline.codec(new PacketEntcoder());
        }).bind(this.port);
    }
}
