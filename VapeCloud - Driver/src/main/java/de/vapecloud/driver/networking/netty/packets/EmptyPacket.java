package de.vapecloud.driver.networking.netty.packets;
/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

public abstract class EmptyPacket extends Packet{

    protected EmptyPacket(int id) {
        super(id);
    }

}
