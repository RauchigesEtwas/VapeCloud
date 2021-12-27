package de.vapecloud.driver.console.setup;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/20:10
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.console.logger.enums.MessageType;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class StartupSetup {


    @SneakyThrows
    public StartupSetup(String line) {
        if(VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep == 0){
            if(line.equalsIgnoreCase("cluster")){

                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the IP of the manager?", "127.0.0.1");
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("INSTANCE", "CLUSTER");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
            }else if(line.equalsIgnoreCase("manager")){

                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the IP of the manager?", "127.0.0.1");
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("INSTANCE", "MANAGER");

                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
            }else {
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the input was incorrect, choose between manager and cluster");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
            }
        }else{
            if(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("INSTANCE").equalsIgnoreCase("MANAGER")){
                managerSetup(line);
            }else if(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("INSTANCE").equalsIgnoreCase("CLUSTER")){
                clusterSetup(line);
            }
        }
    }

    @SneakyThrows
    private void managerSetup(String line){
        switch (VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep){
            case 1:
                if (line.contains(".")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what should be the server splitter? | Default: §e-");
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("ADDRESS", line);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the input was incorrect, we need an address like 127.0.0.1");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 2:
                if(line.equalsIgnoreCase("#") || line.equalsIgnoreCase("-") || line.equalsIgnoreCase("~")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "On which port should the proxies start? | Default: 25565");
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("SPLITTER", line);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);

                } else {
                  VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "you can choose only one of the following splitters: §e#§7, §e-§7, §e~");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 3:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "On which port should the service start? | Default: 4000");
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("PROXYPORT", line);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may only contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 4:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the setup was successful");
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "This cloud instance will now run as a manager, the cloud instance will be restarted shortly...");
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("SERVERPORT", line);

                    HashMap<String, String> messages = new HashMap<>();
                    messages.put("cloud-global-prefix", "§8⮞ §bVape§fCloud §8• §7");
                    messages.put("cloud-proxy-full-join-kick", "§8⮞ §cis currently full, pleas by Premium");
                    messages.put("cloud-proxy-maintenance-kick", "§8⮞ §cis currently in Maintenance");
                    messages.put("cloud-proxy-no-fallback-found-kick", "§8⮞ §cno Lobby was Found");
                    messages.put("cloud-proxy-only-proxy-join-kick", "§8⮞ §cplease join over the Mainprox");
                    messages.put("cloud-spigot-already-on-fallback", "§8⮞ §cyou are already on a lobby");
                    messages.put("cloud-spigot-no-fallback-found", "§8⮞ §cno Lobby was Found");
                    ServiceConfig serviceConfig = new ServiceConfig(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("SPLITTER"),Integer.valueOf(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("PROXYPORT")), Integer.valueOf(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("SERVERPORT")), messages);
                    new ConfigHandler("./service.json").saveConfig( serviceConfig );

                    String authKey = UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString();

                    SettingsConfig settingsConfig = new SettingsConfig(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("ADDRESS"), "manager", authKey, 9302);

                    new ConfigHandler("./settings.json").saveConfig(settingsConfig);


                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();


                    new File("./running/").mkdirs();
                    new File("./module/").mkdirs();
                    new File("./local/templates").mkdirs();
                    new File("./local/GLOBAL").mkdirs();
                    new File("./local/storage").mkdirs();

                    Thread.sleep(10000);
                    System.exit(0);
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may only contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
        }
    }

    @SneakyThrows
    private void clusterSetup(String line){
        switch (VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep){
            case 1:
                if (line.contains(".")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the name of the cluster?");
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("ADDRESS", line);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the input was incorrect, we need an address like 127.0.0.1");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 2:
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the authentication key?");
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("CLUSTERNAME", line);
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                break;
            case 3:
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the setup was successful");
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "This cloud instance will now run as a cluster, the cloud instance will be restarted shortly...");
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("AUTHKEY", line);
                SettingsConfig settingsConfig = new SettingsConfig(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("ADDRESS"), VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CLUSTERNAME"), line, 9302);

                new ConfigHandler("./settings.json").saveConfig(settingsConfig);

                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();
                new File("./running/").mkdirs();
                new File("./local/templates").mkdirs();
                new File("./local/GLOBAL").mkdirs();
                new File("./local/storage").mkdirs();

                Thread.sleep(10000);
                System.exit(0);
                break;
        }
    }
}
