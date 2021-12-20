package de.vapecloud.driver.commandsystem;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class ICommandSender {


    private String targetName;
    private String senderName;
    private String task;
    private String permission;


    public ICommandSender(String targetName, String senderName, String task, String permission) {
        this.targetName = targetName;
        this.senderName = senderName;
        this.task = task;
        this.permission = permission;
    }

    public String getTargetName() {
        return targetName;
    }

    public String getSenderName() {
        return senderName;
    }


    public String getPermission() {
        return permission;
    }

    public String getTask() {
        return task;
    }
}
