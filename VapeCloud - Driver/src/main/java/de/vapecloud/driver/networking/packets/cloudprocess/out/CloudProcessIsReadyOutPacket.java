package de.vapecloud.driver.networking.packets.cloudprocess.out;

import de.vapecloud.vapenet.protocol.IPacketBuffer;
import de.vapecloud.vapenet.protocol.Packet;

import java.util.HashMap;
import java.util.Map;

public class CloudProcessIsReadyOutPacket extends Packet {


    private Map<String, String> data = new HashMap<>();

    public CloudProcessIsReadyOutPacket() {}

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.write("data", this.data);
    }

    @Override
    public void read(IPacketBuffer buffer) {

        this.data =  buffer.readMap("data", String.class, String.class);
    }


    public void setCurrentCluster(String cluster){
        this.data.put("currentcluster", cluster);
    }

    public void setProcessName(String name){
        this.data.put("processname", name);
    }
    public void setPort(Integer port){
        this.data.put("processport", String.valueOf(port));
    }


    public void setProcessType(String type){
        this.data.put("processtype", type);
    }

    public String getCurrentCluster(){
        return this.data.get("currentcluster");
    }

    public String getProcessName(){
        return this.data.get("processname");
    }

    public Integer getProcessPort(){
        return Integer.valueOf(this.data.get("processport"));
    }

    public String getProcessType(){
        return this.data.get("processtype");
    }


}
