package de.vapecloud.vapenet.handlers.listener;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:15
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.IPacketEvent;

public class ClientConnectEvent implements IPacketEvent {

    private VapeNETChannel channel;

    public ClientConnectEvent(VapeNETChannel channel) {
        this.channel = channel;
    }

    public VapeNETChannel getChannel() {
        return channel;
    }
}
