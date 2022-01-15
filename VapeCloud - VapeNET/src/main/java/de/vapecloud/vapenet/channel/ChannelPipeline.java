package de.vapecloud.vapenet.channel;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:39
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNetBootStrap;
import de.vapecloud.vapenet.handlers.bin.PacketListener;
import de.vapecloud.vapenet.protocol.codec.IPacketDecoder;
import de.vapecloud.vapenet.protocol.codec.IPacketEncoder;

public class ChannelPipeline {

    private IPacketEncoder encoder = new IPacketEncoder.NetPacketEncoder();
    private IPacketDecoder decoder = new IPacketDecoder.NetPacketDecoder();



    public ChannelPipeline addLast(IPacketEncoder encoder) {
        if (encoder == null) return this;
        this.encoder = encoder;
        return this;
    }

    public ChannelPipeline addLast(IPacketDecoder decoder) {
        if (decoder == null) return this;
        this.decoder = decoder;
        return this;
    }


    public void addLast(PacketListener packetListener){
        VapeNetBootStrap.packetListenerHandler.registerListener(packetListener);
    }

    public IPacketEncoder getEncoder() {
        return encoder;
    }

    public IPacketDecoder getDecoder() {
        return decoder;
    }

}
