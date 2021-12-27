package de.vapecloud.vapenet.protocol.codec;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:38
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;
import de.vapecloud.vapenet.protocol.PacketBuffer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IPacketDecoder {


    Packet decode(Packet packet, PacketBuffer buffer) throws IOException;

    class NetPacketDecoder implements IPacketDecoder {

        @Override
        public Packet decode(Packet packet, PacketBuffer buffer) {
            try {
                String clazz = buffer.read("clazz", String.class);
                if (clazz == null) return new Packet();

                Packet instance = (Packet) Class.forName(clazz).getConstructor().newInstance();
                instance.read(buffer);
                return instance;
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new Packet();
        }
    }
}
