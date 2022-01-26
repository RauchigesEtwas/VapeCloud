package de.vapecloud.vapenet.protocol;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:37
 * Created by Robin B. (RauchigesEtwas)
 */

public abstract class Packet {

    public abstract void write(IPacketBuffer buffer);

    public abstract void read(IPacketBuffer buffer);
}
