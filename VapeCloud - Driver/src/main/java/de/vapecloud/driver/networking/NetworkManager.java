package de.vapecloud.driver.networking;
/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 * in Cooperation with NikCloud
 */

import de.vapecloud.driver.networking.client.NettyClient;
import de.vapecloud.driver.networking.server.NettyServer;

public class NetworkManager {


    public void startServer(int port) {

        /**
         * create an new NettyServer Connection
         *
         * @param Thread new Thread
         */
        Thread nettyServerThread = new Thread(new NettyServer(port));
        nettyServerThread.start();
    }
    public void startClient(String host, int port) {

        /**
         * create an new NettyClient Connection
         * @using PORT + ADDRESS
         *
         * @param Thread new Thread
         */
        Thread nettyClientThread = new Thread(new NettyClient(host, port));
        nettyClientThread.start();
    }
    public void startClient(int port) {

        /**
         * create an new NettyClient Connection
         * @using PORT
         *
         * @param Thread new Thread
         */
        Thread nettyClientThread = new Thread(new NettyClient("127.0.0.1", port));
        nettyClientThread.start();
    }
}
