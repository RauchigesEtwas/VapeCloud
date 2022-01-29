package de.vapecloud.bungee.utils.server;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.Map;

public class ServerManager {

    public static boolean serverExists(String name) {
        return getServerInfo(name) != null;
    }

    public static ServerInfo getServerInfo(String name) {
        return getServers().get(name);
    }

    public static void addServer(ServerInfo serverInfo) {
        if (serverExists(serverInfo.getName())) {
            return;
        }

        getServers().put(serverInfo.getName(), serverInfo);
        ServerConfig.addToConfig(serverInfo);
    }

    public static void removeServer(String name) {
        if (!serverExists(name)) {
            return;
        }

        getServers().remove(name);
        ServerConfig.removeFromConfig(name);
    }

    public static Map<String, ServerInfo> getServers() {
        return ProxyServer.getInstance().getServers();
    }

}
