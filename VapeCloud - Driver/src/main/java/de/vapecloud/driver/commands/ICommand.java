package de.vapecloud.driver.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;

public abstract class ICommand {


    private String name;
    private String[] aliases;
    private String description;

    public ICommand(String commandname, String description, String... aliases) {
        this.name = commandname;
        this.description = description;
        this.aliases = aliases;
    }

    public abstract boolean execute(ICommand command, ICommandSender sender, String[] args);

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAliases() {
        ArrayList<String> resuls = new ArrayList<>();
        for (String al : aliases){
            resuls.add(al);
        }
        return resuls;
    }

}
