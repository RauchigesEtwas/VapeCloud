package de.vapecloud.launcher.manager;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.launcher.manager.commands.ClearConsolCommand;
import de.vapecloud.launcher.manager.commands.ExitCommand;
import de.vapecloud.launcher.manager.commands.HelpCommand;

public class VapeManager {

    public void initVapeManager(){


        VapeDriver.consolHandler.getCommandHandler().registerCommand(new HelpCommand("help", "here you will find all commands", "?", "ls", "ask"));
        VapeDriver.consolHandler.getCommandHandler().registerCommand(new ClearConsolCommand("clear", "clean the console of the cloud", "cls"));
        VapeDriver.consolHandler.getCommandHandler().registerCommand(new ExitCommand("exit", "Shut down the cloud completely", "shutdown", "kill", "quit", "end"));



        while (true){}

    }

}
