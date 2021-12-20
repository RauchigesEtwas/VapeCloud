package de.vapecloud.driver.networking.netty.packets;


import java.util.ArrayList;
import java.util.HashMap;

public class PacketManager {

    //private ArrayList<Class<? extends Packet>> packets = new ArrayList<>();
    private HashMap<Integer, Class<? extends Packet>> packets = new HashMap<>();

    private HashMap<Integer, Class<? extends Packet>> incomingPackets = new HashMap<>();
    private HashMap<Integer, Class<? extends Packet>> outgoingPackets = new HashMap<>();

    public PacketManager() {
        //addPacket(1, TestPacket.class);
        //addPacket(TestPacket.class);
    }

    public void addPacket(int id, Class<? extends Packet> packetClass) {
        packets.put(id, packetClass);
        //packets.add(packetClass);
    }

    public void addIncomingPacket(int id, Class<? extends Packet> packetClass) {
        incomingPackets.put(id, packetClass);
    }
    public void addOutgoingPacket(int id, Class<? extends Packet> packetClass) {
        outgoingPackets.put(id, packetClass);
    }

    public Class<? extends Packet> getIncomingPacketFromId(int id) {
        return incomingPackets.get(id);
    }
    public int getIdFromOutgoingPacket(Packet packet) {
        for (int id : outgoingPackets.keySet()) {
            if (packet.getClass().equals(packets.get(id)))
                return id;
        }
        return -1;
    }

    public int getIdFromPacket(Packet packet) {
        for (int id : packets.keySet()) {
            if (packet.getClass().equals(packets.get(id)))
                return id;
        }
        return -1;
        //return packets.indexOf(packet.getClass());
    }
    public Class<? extends Packet> getPacketFromId(int id) {
        return packets.get(id);
    }
}
