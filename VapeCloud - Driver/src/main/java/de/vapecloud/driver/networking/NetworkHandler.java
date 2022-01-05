package de.vapecloud.driver.networking;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/18:19
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.networking.client.Client;
import de.vapecloud.driver.networking.server.Server;

public class NetworkHandler {

    public Client client;
    public Server server;
    private ConnectionHandler connectionHandler;


    public NetworkHandler() {
        this.connectionHandler = new ConnectionHandler();
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }
}
