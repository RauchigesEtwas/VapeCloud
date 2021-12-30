package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/20:52
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commandsystem.ICommand;
import de.vapecloud.driver.commandsystem.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;

public class ProcessCommand extends ICommand {
    public ProcessCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {

        if(args.length == 0){
            sendHelp(command);
        }

        return false;
    }


    private void sendHelp(ICommand command){
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "The following Commands are registered:");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " run <containername> <count> §7~ start a new server container");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " stop <runningcontainer> §7~ Stop a running server container");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " stopContainer <containername> §7~ Stop all running server containers from one container");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " list §7~ see all running process on the cloud");

    }
}
