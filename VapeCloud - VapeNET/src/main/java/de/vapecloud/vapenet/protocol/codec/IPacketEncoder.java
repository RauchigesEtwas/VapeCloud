package de.vapecloud.vapenet.protocol.codec;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:38
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETUtil;
import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

import java.io.DataOutputStream;
import java.io.IOException;

public interface IPacketEncoder {

    void encode(DataOutputStream output, Packet packet, IPacketBuffer buffer) throws IOException;

    class NetPacketEncoder implements IPacketEncoder {
        @Override
        public void encode(DataOutputStream output, Packet packet, IPacketBuffer buffer) throws IOException {
            buffer.write("clazz", packet.getClass().getName());
            packet.write(buffer);
            output.write(VapeNETUtil.nullChar(buffer.array()));
            output.flush();
        }
    }
}
