package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/10:29
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.container.enums.ContainerVersion;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.driver.process.interfaces.IProcess;
import de.vapecloud.driver.utils.download.Downloader;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/plugins").mkdirs();
            new Downloader("server.jar", "./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0], ContainerVersion.valueOf(this.getProcessCore().getProcessVersion()));
            Thread.sleep(500);
        }
        //create live-folder
        new File("." + this.getProcessCore().getRunningPath()+ "plugins/").mkdirs();
        //copy all Data form Client
        if(!this.getProcessCore().isStaticProcess()){
            FileUtils.copyDirectory(new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/"), new File("." + this.getProcessCore().getRunningPath()));
        }else{
            if (!new File("."+ this.getProcessCore().getRunningPath()).exists()){
                FileUtils.copyDirectory(new File("./local/templates/"+this.getProcessCore().getProcessName().split(this.getProcessCore().getSplitter())[0] + "/"), new File("." + this.getProcessCore().getRunningPath()));
            }
        }
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
            fileWriter.write(VapeDriver.getInstance().getVapeSettings().getDataCenter().getBungeeConfig(this.getProcessCore().getProvideStartPort(),1));
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

            Thread thread = new Thread(() -> {
                try {
                    process = Runtime.getRuntime().exec("java -Xms" + getProcessCore().getMaximalMemory() + "M -Xmx" + getProcessCore().getMaximalMemory() + "M -Dcom.mojang.eula.agree=true -jar server.jar -org.spigotmc.netty.disabled=true --port " + getProcessCore().getProvideStartPort() + " ", null, new File(System.getProperty("user.dir") + getProcessCore().getRunningPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    @Override
    public void stopProcess() {
        process.destroy();
    }


}
