package de.vapecloud.driver.networking.bin;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/22:04
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.VapeNETUtil;
import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;
import de.vapecloud.vapenet.protocol.codec.IPacketEncoder;

import java.io.DataOutputStream;
import java.io.IOException;

public class PacketEntcoder implements IPacketEncoder {
    @Override
    public void encode(DataOutputStream output, Packet packet, IPacketBuffer buffer) throws IOException {
        buffer.write("clazz", packet.getClass().getName());
        packet.write(buffer);
        output.write(VapeNETUtil.nullChar(buffer.array()));
        output.flush();
    }
}
