package de.vapecloud.driver.networking.base.packets;
/*
 * Created AT: 20.12.2021
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    //STRING
    public void writeString(ByteBuf byteBuf, String message) {
        byteBuf.writeInt(message.length());
        byteBuf.writeCharSequence(message, StandardCharsets.UTF_8);
    }
    public String readString(ByteBuf byteBuf) {
        int messageLength = byteBuf.readInt();
        return byteBuf.readCharSequence(messageLength, StandardCharsets.UTF_8).toString();
    }

    //INT
    public void writeInt(ByteBuf byteBuf, int integer) {
        byteBuf.writeInt(integer);
    }
    public int readInt(ByteBuf byteBuf) {
        return byteBuf.readInt();
    }

    //BYTE
    public void writeByte(ByteBuf byteBuf, Byte Byte) {
        byteBuf.writeByte(Byte);
    }
    public Byte readByte(ByteBuf byteBuf) {
        return byteBuf.readByte();
    }

    //LONG
    public void writeLong(ByteBuf byteBuf, Long Long) {
        byteBuf.writeLong(Long);
    }
    public Long readLong(ByteBuf byteBuf) {
        return byteBuf.readLong();
    }

    //DOUBLE
    public void writeDouble(ByteBuf byteBuf, Double Double) {
        byteBuf.writeDouble(Double);
    }
    public Double readDouble(ByteBuf byteBuf) {
        return byteBuf.readDouble();
    }

    //CHAR
    public void writeChar(ByteBuf byteBuf, char Char) {
        byteBuf.writeChar(Char);
    }
    public char readChar(ByteBuf byteBuf) {
        return byteBuf.readChar();
    }

    //ARRAYLIST
    public void writeArrayList(ByteBuf byteBuf, ArrayList<String> Array) {

        String message = "";
        if (Array.size() == 1){
            message = Array.get(1).toString();
        }else{
            for (int i = 0; i!= Array.size(); i++){
                if(i == 0){
                    message = Array.get(i);
                }else{
                    message = message + ", " + Array.get(i);
                }
            }
        }
        byteBuf.writeInt(message.length());
        byteBuf.writeCharSequence(message, StandardCharsets.UTF_8);
    }
    public ArrayList<String> readArryList(ByteBuf byteBuf) {
        int messageLength = byteBuf.readInt();
        ArrayList<String> array = new ArrayList<>();

        String resuls = byteBuf.readCharSequence(messageLength, StandardCharsets.UTF_8).toString();

        String[]output =  resuls.split(", ");
        for (String out : output){
            array.add(out);
        }
        return array;
    }


    //SHORT
    public void writeShort(ByteBuf byteBuf, Short Short) {
        byteBuf.writeShort(Short);
    }
    public Short readShort(ByteBuf byteBuf) {
        return byteBuf.readShort();
    }

    //FLOAT
    public void writeFloat(ByteBuf byteBuf, float Float) {
        byteBuf.writeFloat(Float);
    }
    public float readFloat(ByteBuf byteBuf) {
        return byteBuf.readFloat();
    }

    //BOOLEAN
    public void writeBoolean(ByteBuf byteBuf, boolean bool) {
        byteBuf.writeBoolean(bool);
    }
    public boolean readBoolean(ByteBuf byteBuf) {
        return byteBuf.readBoolean();
    }

}
