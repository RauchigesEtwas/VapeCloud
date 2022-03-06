package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/10:29
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import de.vapecloud.driver.configuration.configs.ServiceConfig;
import de.vapecloud.driver.configuration.configs.SettingsConfig;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.enums.ContainerVersion;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.driver.process.interfaces.IProcess;
import de.vapecloud.driver.utils.download.Downloader;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RunningProcess implements IProcess {

    private ProcessCore processCore;
    private Process process;


    public RunningProcess() {}

    public ProcessCore getProcessCore() {
        return processCore;
    }

    public void setProcessCore(ProcessCore processCore) {
        this.processCore = processCore;
    }

    @SneakyThrows
    @Override
    public void runProcess() {
        if(!new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/").exists()){
            if (this.getProcessCore().isStaticProcess()){
                System.out.println("ttt");
                new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/defaults/plugins").mkdirs();
                new Downloader("server.jar", "./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/defaults/", ContainerVersion.valueOf(this.getProcessCore().getProcessVersion()));
                Thread.sleep(500);
            }else {
                new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/plugins").mkdirs();
                new Downloader("server.jar", "./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0], ContainerVersion.valueOf(this.getProcessCore().getProcessVersion()));
                Thread.sleep(500);
            }
        }
        //create live-folder
        new File("." + this.getProcessCore().getRunningPath()+ "plugins/").mkdirs();
        //copy all Data form Client
        if(!this.getProcessCore().isStaticProcess()){
            FileUtils.copyDirectory(new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/"), new File("." + this.getProcessCore().getRunningPath()));
        }else{
            if (new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/" + this.getProcessCore().getProcessName()+"/").exists()){
                FileUtils.copyDirectory(new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/" + this.getProcessCore().getProcessName() + "/"), new File("." + this.getProcessCore().getRunningPath()));
            }else {
                FileUtils.copyDirectory(new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/defaults/"), new File("." + this.getProcessCore().getRunningPath()));
            }

        }
        new File("."+ this.getProcessCore().getRunningPath()+ "/cloud/").mkdirs();
        SettingsConfig settingsConfig = (SettingsConfig)new ConfigHandler("./settings.json").getConfig(SettingsConfig.class);

        ProcessConfig config = new ProcessConfig();
        config.setMessages(new HashMap<>());
        config.setRunningCluster(processCore.getRunningCluster());
        config.setProcessName(processCore.getProcessName());
        config.setProcessMode(processCore.getProcessModeType());
        config.setProcessStartPort(processCore.getProvideStartPort());
        config.setPort(settingsConfig.getInternalPort());
        config.setManagerAddress(settingsConfig.getManagerAddresse());
        new ConfigHandler("."+ this.getProcessCore().getRunningPath()+ "/cloud/config.json").saveConfig(config);
        FileUtils.copyDirectory(new File("./local/GLOBAL/"), new File("." + this.getProcessCore().getRunningPath()));
        VapeDriver.getInstance().getModuleHandler().getModuleDataCache().forEach((module, type) -> {
            if (type.equalsIgnoreCase("BOTH")){
                try {
                    FileUtils.copyFile(new File("./modules/" + module+ ".jar"), new File("." + getProcessCore().getRunningPath()+"/plugins/"+ module+"jar") );
                } catch (IOException ignored) {}

            }else if (type.equalsIgnoreCase("SERVER")){
                if (!this.getProcessCore().getProcessModeType().equalsIgnoreCase("PROXY")){
                    try {
                        FileUtils.copyFile(new File("./modules/" + module+ ".jar"), new File("." + getProcessCore().getRunningPath()+"/plugins/"+ module+"jar") );
                    } catch (IOException ignored) {}
                }
            }else if (type.equalsIgnoreCase("PROXY")){
                if (this.getProcessCore().getProcessModeType().equalsIgnoreCase("PROXY")){
                    try {
                        FileUtils.copyFile(new File("./modules/" + module+ ".jar"), new File("." + getProcessCore().getRunningPath()+"/plugins/"+ module+"jar") );
                    } catch (IOException ignored) {}
                }
            }
        });
        if (this.getProcessCore().getProcessModeType().equalsIgnoreCase("PROXY")){
            FileUtils.copyFile(new File("./local/server-icon.png"), new File("." + this.getProcessCore().getRunningPath()+ "server-icon.png"));
            File configFile = new File(System.getProperty("user.dir") + this.getProcessCore().getRunningPath(), "config.yml");
            final FileWriter fileWriter = new FileWriter(configFile);
            ServiceConfig serviceConfig = (ServiceConfig) new ConfigHandler("./service.json").getConfig(ServiceConfig.class);
            fileWriter.write(VapeDriver.getInstance().getVapeSettings().getDataCenter().getBungeeConfig(this.getProcessCore().getProvideStartPort(),new ContainerHandler().getSubContainers(this.getProcessCore().getProcessName().split(serviceConfig.getInternalSplitter())[0]).getMaximalPlayers()));
            fileWriter.flush();
            fileWriter.close();

            Thread thread = new Thread(() -> {
                try {
                    process = Runtime.getRuntime().exec("java -Xms" + getProcessCore().getMaximalMemory() + "M -Xmx" + getProcessCore().getMaximalMemory() + "M -jar server.jar", null, new File(System.getProperty("user.dir") + getProcessCore().getRunningPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        }else {
            File configFile = new File(System.getProperty("user.dir") + this.getProcessCore().getRunningPath(), "server.properties");
            final FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(VapeDriver.getInstance().getVapeSettings().getDataCenter().getSpigotConfig());
            fileWriter.flush();
            fileWriter.close();

            File configFile2 = new File(System.getProperty("user.dir") + this.getProcessCore().getRunningPath(), "bukkit.yml");
            final FileWriter fileWriter2 = new FileWriter(configFile2);
            fileWriter2.write(VapeDriver.getInstance().getVapeSettings().getDataCenter().getSpigotConfigs());
            fileWriter2.flush();
            fileWriter2.close();

            ServiceConfig serviceConfig = (ServiceConfig) new ConfigHandler("./service.json").getConfig(ServiceConfig.class);

            Thread thread = new Thread(() -> {
                try {

                    process = Runtime.getRuntime().exec("java -Xms" + getProcessCore().getMaximalMemory() + "M -Xmx" + getProcessCore().getMaximalMemory() + "M -Dcom.mojang.eula.agree=true -jar server.jar -org.spigotmc.netty.disabled=true --port " + getProcessCore().getProvideStartPort() + " --max-players "+new ContainerHandler().getSubContainers(this.getProcessCore().getProcessName().split(serviceConfig.getInternalSplitter())[0]).getMaximalPlayers()+" ", null, new File(System.getProperty("user.dir") + getProcessCore().getRunningPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    @SneakyThrows
    @Override
    public void stopProcess() {
       if (process.isAlive()){
           process.destroy();
       }
       Thread.sleep(600);
       if (!getProcessCore().isStaticProcess()){
            FileUtils.deleteDirectory(new File("." + this.getProcessCore().getRunningPath()));
           File file = new File("./live/"+  this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/");
           if (file.list().length == 0) {
               file.delete();
           }
       }  else{
           new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/" + this.getProcessCore().getProcessName()+"/").delete();
           new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/" + this.getProcessCore().getProcessName()+"/").mkdirs();
           FileUtils.copyDirectory(new File("." + this.getProcessCore().getRunningPath()), new File("./local/templates/" + this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/" + this.getProcessCore().getProcessName()+"/"));
           Thread.sleep(200);
           FileUtils.deleteDirectory(new File("." + this.getProcessCore().getRunningPath()));
           File file = new File("./live/"+  this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0]+ "/");
           if (file.list().length == 0) {
               file.delete();
           }
       }
        File file = new File("./live/");
        if (file.list().length == 0) {
            file.delete();
        }
    }


}
