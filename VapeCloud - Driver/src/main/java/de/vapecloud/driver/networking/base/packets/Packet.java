package de.vapecloud.driver.networking.base.packets;
/*
 * Created AT: 20.12.2021
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public abstract class Packet implements IPacket{
    private int id;

    protected Packet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void writeString(ByteBuf byteBuf, String message) {
        byteBuf.writeInt(message.length());
        byteBuf.writeCharSequence(message, StandardCharsets.UTF_8);
    }
    public String readString(ByteBuf byteBuf) {
        int messageLength = byteBuf.readInt();
        return byteBuf.readCharSequence(messageLength, StandardCharsets.UTF_8).toString();
    }
    public void writeInt(ByteBuf byteBuf, int integer) {
        byteBuf.writeInt(integer);
    }
    public int readInt(ByteBuf byteBuf) {
        return byteBuf.readInt();
    }
    public void writeBoolean(ByteBuf byteBuf, boolean bool) {
        byteBuf.writeBoolean(bool);
    }
    public boolean readBoolean(ByteBuf byteBuf) {
        return byteBuf.readBoolean();
    }

}
