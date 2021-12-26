package de.vapecloud.driver.processes;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/16:11
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.processes.interfaces.IProcess;

public class TaskProcess implements IProcess {



    private Thread task, consoleOutPut;
    private Process process;
    private TaskProcess taskProcess;

    public TaskProcess(TaskProcess taskProcess) {
        this.taskProcess = taskProcess;
    }


    @Override
    public void startProcess() {

    }

    @Override
    public void shutdownProcess() {

    }



}
