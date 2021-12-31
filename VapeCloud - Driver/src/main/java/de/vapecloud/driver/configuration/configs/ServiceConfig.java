package de.vapecloud.driver.configuration.configs;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/12:55
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.configuration.IConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceConfig implements IConfig {



    private String internalSplitter;
    private Integer proxyStartupPort;
    private Integer serverStartupPort;
    private ArrayList<String> addresses;
    private HashMap<String, String> messages;

    public ServiceConfig() {}

    public ServiceConfig(String internalSplitter, Integer proxyStartupPort, Integer serverStartupPort, ArrayList<String> addresses, HashMap<String, String> messages) {
        this.internalSplitter = internalSplitter;
        this.proxyStartupPort = proxyStartupPort;
        this.serverStartupPort = serverStartupPort;
        this.addresses = addresses;
        this.messages = messages;
    }

    public ArrayList<String> getAddresses() {
        return addresses;
    }

    public String getInternalSplitter() {
        return internalSplitter;
    }

    public Integer getProxyStartupPort() {
        return proxyStartupPort;
    }

    public Integer getServerStartupPort() {
        return serverStartupPort;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }
}
