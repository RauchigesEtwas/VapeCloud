package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/15:11
 * Created by Robin B. (RauchigesEtwas)
 */

import io.netty.channel.Channel;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashMap;

public class ClientManager {

    private HashMap<String, Channel> connectedClients = new HashMap<>();
     private HashMap<String, Channel> nonAuthClient = new HashMap<>();

    public ClientManager() {}

    public Channel getChannel(String clientName){
        if(connectedClients.containsKey(clientName)){
            return connectedClients.get(clientName);
        }
        return null;
    }

    public boolean isKeyExits(String key){
        if (nonAuthClient.containsKey(key)){
            return true;
        }else {
            return false;
        }
    }

    @SneakyThrows
    public void createChannel(String key, Channel channel){
        if(!nonAuthClient.containsKey(key)){
            nonAuthClient.put(key, channel);
        }
    }

    @SneakyThrows
    public void verifyChannel(String key, String clientName){
        if(nonAuthClient.containsKey(key)){
               Channel channel = nonAuthClient.get(key);
               nonAuthClient.remove(key);
              connectedClients.put(clientName, channel);
        }
    }

    @SneakyThrows
    public void deleteChannel(String clientName){
        if(connectedClients.containsKey(clientName)){
            connectedClients.get(clientName).close();
            connectedClients.remove(clientName);
        }
    }

}
