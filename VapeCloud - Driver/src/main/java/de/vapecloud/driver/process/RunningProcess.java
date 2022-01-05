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

    }

    @Override
    public void stopProcess() {

    }


}
