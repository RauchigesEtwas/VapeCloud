package de.vapecloud.driver.commandsystem;
/*
 * Created AT: 08.12.2021
 * Created by Robin B. (UniqueByte)
 */

import de.vapecloud.driver.VapeDriver;
import java.util.HashSet;
import java.util.Set;

public class ICommandHandler {

    private Set<ICommand> commands;

    public ICommandHandler() {
        this.commands = new HashSet<>();
    }


    public void registerCommand(ICommand command){
        commands.add(command);
    }

    public void unregisterCommand(ICommand command){
        commands.remove(command);
    }

    public void executeCommand(String line, ICommandSender sender){
        ICommand command = getCommand(line.split(" ")[0]);
        String[] args = VapeDriver.dataCenter.dropFirstString(line.split(" "));
        if(command != null){
            if(VapeDriver.consolHandler.isAlive()){
                command.execute(command,sender, args);
            }
        }

    }

    public ICommand getCommand(String name){
        for (ICommand command : this.commands){
            if(command.getCommand().equalsIgnoreCase(name)){
                return command;
            }
            if (command.getAliases().contains(name)){
                return command;
            }
        }
        return null;
    }
}
