package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/22:18
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commandsystem.ICommand;
import de.vapecloud.driver.commandsystem.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;

public class ReloadCommand extends ICommand {
    public ReloadCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "the cloud will now be reloaded");
        VapeDriver.getInstance().getModuleHandler().reloadModules();
        return false;
    }
}
