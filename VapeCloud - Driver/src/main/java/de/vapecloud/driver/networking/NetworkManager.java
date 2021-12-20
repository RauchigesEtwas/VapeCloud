package de.vapecloud.driver.networking;
/*
 * Created AT: 20.12.2021
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.netty.NettyClient;
import de.vapecloud.driver.networking.netty.NettyServer;

public class NetworkManager {


    public void startServer(int port) {
        Thread nettyServerThread = new Thread(new NettyServer(port));
        nettyServerThread.start();
    }
    public void startClient(String ip, int port) {
        Thread nettyClientThread = new Thread(new NettyClient(ip, port));
        nettyClientThread.start();
    }
    public void startClient(int port) {
        Thread nettyClientThread = new Thread(new NettyClient("127.0.0.1", port));
        nettyClientThread.start();
    }
}
