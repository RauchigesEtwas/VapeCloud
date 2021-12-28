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
import lombok.SneakyThrows;

public class Client {

    private int port;
    private String host;

    public Client() {}


    public Client bind(String host, int port){
        this.host = host;
        this.port = port;
        return this;
    }

    @SneakyThrows
    public void create(){
        new VapeNetBootStrap();
        VapeNetBootStrap.getInstance().client = new VapeNETClient();
        VapeNetBootStrap.getInstance().client.init(channel -> {
            ChannelPipeline pipeline = channel.getPipeline();
            pipeline.codec(new PacketDecoder());
            pipeline.codec(new PacketEntcoder());

        }).bind(this.host, this.port).option(VapeNETOption.DENNY_NIO, false).option(VapeNETOption.KEEPALIVE, this).connect();
    }
}
