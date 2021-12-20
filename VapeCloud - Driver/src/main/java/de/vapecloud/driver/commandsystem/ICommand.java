package de.vapecloud.driver.commandsystem;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;

public abstract class ICommand {


    private String commandname;
    private String[] aliases;

    public ICommand(String commandname, String... aliases) {
        this.commandname = commandname;
        this.aliases = aliases;
    }

    public abstract boolean execute(ICommand command, ICommandSender sender, String[] args);

    public String getCommand() {
        return commandname;
    }


    public ArrayList<String> getAliases() {
        ArrayList<String> resuls = new ArrayList<>();
        for (String al : aliases){
            resuls.add(al);
        }
        return resuls;
    }

}
