package de.vapecloud.vapenet.handlers.listener;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:24
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.channel.VapeNETChannel;
import de.vapecloud.vapenet.handlers.bin.IPacketEvent;

public class ClientDisconectEvent implements IPacketEvent {
    private VapeNETChannel channel;

    public ClientDisconectEvent(VapeNETChannel channel) {
        this.channel = channel;
    }

    public VapeNETChannel getChannel() {
        return channel;
    }
}
