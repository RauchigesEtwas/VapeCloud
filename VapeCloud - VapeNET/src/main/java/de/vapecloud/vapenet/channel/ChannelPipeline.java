package de.vapecloud.vapenet.channel;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:39
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.codec.IPacketDecoder;
import de.vapecloud.vapenet.protocol.codec.IPacketEncoder;

public class ChannelPipeline {

    private IPacketEncoder encoder = new IPacketEncoder.NetPacketEncoder();
    private IPacketDecoder decoder = new IPacketDecoder.NetPacketDecoder();



    public ChannelPipeline codec(IPacketEncoder encoder) {
        if (encoder == null) return this;
        this.encoder = encoder;
        return this;
    }

    public ChannelPipeline codec(IPacketDecoder decoder) {
        if (decoder == null) return this;
        this.decoder = decoder;
        return this;
    }


    public IPacketEncoder getEncoder() {
        return encoder;
    }

    public IPacketDecoder getDecoder() {
        return decoder;
    }

}
