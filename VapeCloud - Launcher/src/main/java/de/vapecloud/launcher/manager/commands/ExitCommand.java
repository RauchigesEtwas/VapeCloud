package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/18:54
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commands.ICommand;
import de.vapecloud.driver.commands.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;

import java.util.Timer;
import java.util.TimerTask;

public class ExitCommand extends ICommand {
    public ExitCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {

        if(VapeDriver.getInstance().getVapeSettings().isLikeShutdown()){
            System.exit(0);
        }else{
            VapeDriver.getInstance().getVapeSettings().setLikeShutdown(true);

            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION, true, "please enter the §ecommand§7 again to confirm you have §e15 seconds §7to do so");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    VapeDriver.getInstance().getVapeSettings().setLikeShutdown(false);
                }
            },1000*15);

        }
        return false;
    }
}
