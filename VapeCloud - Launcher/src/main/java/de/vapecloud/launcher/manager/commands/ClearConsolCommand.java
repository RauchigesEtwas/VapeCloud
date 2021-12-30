package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/18:51
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commandsystem.ICommand;
import de.vapecloud.driver.commandsystem.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;

public class ClearConsolCommand extends ICommand {

    public ClearConsolCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {

        VapeDriver.getInstance().getConsolHandler().clearScreen();
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.EMPTY,true, VapeDriver.getInstance().getVapeSettings().getDataCenter().getLogo());
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "the console has been §ecleaned");


        return false;
    }
}
