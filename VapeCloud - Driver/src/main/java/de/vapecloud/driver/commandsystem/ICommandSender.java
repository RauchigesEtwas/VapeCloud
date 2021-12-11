package de.vapecloud.driver.commandsystem;
/*
 * Created AT: 08.12.2021
 * Created by Robin B. (UniqueByte)
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
