package de.vapecloud.bukkit.utils;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.networking.client.Client;

public class GeneralHandler {

    public GeneralHandler() {}


    public void buildNetworking(){

        ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);

        VapeDriver.getInstance().getNetworkHandler().client = new Client();
        VapeDriver.getInstance().getNetworkHandler().client.bind(config.getManagerAddress(), config.getPort()).create();


    }

}
