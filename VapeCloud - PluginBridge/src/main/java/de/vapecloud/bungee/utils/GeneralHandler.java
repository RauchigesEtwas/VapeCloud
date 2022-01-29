package de.vapecloud.bungee.utils;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.client.Client;
import java.util.*;

public class GeneralHandler {


    public Map<String, String> currentServerOfPlayers;
    public Integer currentOnlineOnAllProxies = 0;
    public List<String> lobbys;

    public GeneralHandler() {
        currentServerOfPlayers = new HashMap<>();
        lobbys = new ArrayList<>();

    }


}
