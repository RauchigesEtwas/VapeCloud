package de.vapecloud.driver.process;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/10:29
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.process.bin.ProcessCore;
import de.vapecloud.driver.process.interfaces.IProcess;

public class RunningProcess implements IProcess {

    private ProcessCore processCore;


    public RunningProcess() {

    }

    public ProcessCore getProcessCore() {
        return processCore;
    }

    public void setProcessCore(ProcessCore processCore) {
        this.processCore = processCore;
    }

    @Override
    public void runProcess() {

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, false, "The Process §e"+getProcessCore().getProcessName()+"§7 is being §astarted§7! [Cluster: §e"+
                getProcessCore().getRunningCluster()+"§7, Data: §e"+getProcessCore().getProcessModeType()+"§7~§e"+processCore.getProvideStartPort()+"§7@§e"+getProcessCore().getProcessVersion()+"§7]");

    }

    @Override
    public void stopProcess() {

    }


}
