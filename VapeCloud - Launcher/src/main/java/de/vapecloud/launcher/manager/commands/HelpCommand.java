package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/16:04
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commandsystem.ICommand;
import de.vapecloud.driver.commandsystem.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;

public class HelpCommand extends ICommand {

    public HelpCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {

        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "The following Commands are registered:");
        VapeDriver.getInstance().getConsolHandler().getCommandHandler().getCommands().forEach(cmd -> {
            String aliases = "";

            if(cmd.getAliases().isEmpty()){

            }else if (cmd.getAliases().size() == 1){
                aliases = cmd.getAliases().get(0);
            }else {
                for (int i = 0; i !=   cmd.getAliases().size(); i++){
                    if(i == 0){
                        aliases =   cmd.getAliases().get(i);
                    }else{
                        aliases = aliases + ", " +   cmd.getAliases().get(i);
                    }
                }
            }

            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + cmd.getName() + " §7- Aliases: §6[" +aliases+"] §7~ §f" + cmd.getDescription());
        });
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "Threads§7: §e"+ Runtime.getRuntime().availableProcessors());
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "OS§7: §e"+ System.getProperty("os.name"));
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "Support§7: §ehttps://discord.gg/4kKEcaP9W");
        return false;
    }
}
