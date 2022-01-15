package de.vapecloud.launcher.manager.commands;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/13:22
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commands.ICommand;
import de.vapecloud.driver.commands.ICommandSender;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;
import de.vapecloud.driver.container.containers.MainContainer;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.utils.setup.SetupTypes;

import java.util.HashMap;

public class ContainerCommand extends ICommand {

    public ContainerCommand(String commandname, String description, String... aliases) {
        super(commandname, description, aliases);
    }

    @Override
    public boolean execute(ICommand command, ICommandSender sender, String[] args) {
        if (command == null || sender == null)
            return false;
        if(args.length == 0){
            sendHelp(command);
        }else{
            if (args[0].equalsIgnoreCase("create")){
                if (args.length == 2){
                    String conType = args[1];
                    if (conType.equalsIgnoreCase("sub")){
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupTypes = SetupTypes.SUBCONTAINER;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler = new ContainerHandler();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = true;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory = new HashMap<>();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage = new HashMap<>();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep = 0;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.clear();
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,true, "the setup will be §estarted now §7you can cancel it at any time with §e\"cancel\"");
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,true, "what should the §esubcontainer §7be called?");

                    }else if (conType.equalsIgnoreCase("main")){
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupTypes = SetupTypes.MAINCONTAINER;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler = new ContainerHandler();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = true;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory = new HashMap<>();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage = new HashMap<>();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep = 0;
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();
                        VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.clear();
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,true, "the setup will be §estarted now §7you can cancel it at any time with §e\"cancel\"");
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP,true, "what should the §emaincontainer §7be called?");
                    }else {
                        sendHelp(command);
                    }
                }else {
                    sendHelp(command);
                }
            }else if (args[0].equalsIgnoreCase("delSub")){
                if (args.length == 2){
                    ContainerHandler containerHandler = new ContainerHandler();
                    String mainCon = args[1];
                    if (containerHandler.canFindeSubContainer(mainCon)){
                        containerHandler.removeSubContainer(mainCon);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                    }else {
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation §ecould not be executed§7 because the §econtainer§7 was not found");

                    }
                }else {
                    sendHelp(command);
                }
            }else if (args[0].equalsIgnoreCase("delMain")){
                if (args.length == 2){
                    ContainerHandler containerHandler = new ContainerHandler();
                    String mainCon = args[1];
                    if (containerHandler.canFindeMainContainer(mainCon)){
                        containerHandler.removeMainContainer(mainCon);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                    }else {
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation §ecould not be executed§7 because the §econtainer§7 was not found");

                    }
                }else {
                    sendHelp(command);
                }
            }else if (args[0].equalsIgnoreCase("editSub")){
                if (args.length == 4){
                    ContainerHandler containerHandler = new ContainerHandler();
                    String subCon = args[1];
                    String key = args[2];
                    String value = args[3];
                    if (containerHandler.canFindeSubContainer(subCon)){
                        if(key.equalsIgnoreCase("TOTALONLINE") || key.equalsIgnoreCase("MAXIMALONLINE") || key.equalsIgnoreCase("MAINTENANCE") || key.equalsIgnoreCase("MAXIMALPLAYERS")){
                            if (key.equalsIgnoreCase("TOTALONLINE")){
                                SubContainer container = containerHandler.getSubContainers(subCon);
                                container.setTotalOnline(Integer.parseInt(value));
                                containerHandler.editSubContainer(subCon, container);
                                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                            }  if (key.equalsIgnoreCase("MAXIMALONLINE")){
                                SubContainer container = containerHandler.getSubContainers(subCon);
                                container.setMaximalOnline(Integer.parseInt(value));
                                containerHandler.editSubContainer(subCon, container);
                                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                            }  if (key.equalsIgnoreCase("MAINTENANCE")){
                                SubContainer container = containerHandler.getSubContainers(subCon);
                                container.setMaintenance(Boolean.parseBoolean(value));
                                containerHandler.editSubContainer(subCon, container);
                                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                            }  if (key.equalsIgnoreCase("MAXIMALPLAYERS")){
                                SubContainer container = containerHandler.getSubContainers(subCon);
                                container.setMaximalPlayers(Integer.parseInt(value));
                                containerHandler.editSubContainer(subCon, container);
                                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                            }
                        }else {
                            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "You can change the following:");
                            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "Types: §eTOTALONLINE§7, §eMAXIMALONLINE§7, §eMAINTENANCE§7, §eMAXIMALPLAYERS");
                        }
                    }else{
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation cannot be executed because the§e subcontainer §7is not found");
                    }
                }else {
                    sendHelp(command);
                }

            }else if (args[0].equalsIgnoreCase("editSubTypes")){
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "You can change the following:");
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "Types: §eTOTALONLINE§7, §eMAXIMALONLINE§7, §eMAINTENANCE§7, §eMAXIMALPLAYERS");
            }else if (args[0].equalsIgnoreCase("addToMain")){
                if(args.length == 3){
                    ContainerHandler containerHandler = new ContainerHandler();
                    String mainCon = args[1];
                    String subCon = args[2];
                    if(containerHandler.canFindeMainContainer(mainCon) && containerHandler.canFindeSubContainer(subCon)){
                        MainContainer container = containerHandler.getMainContainer(mainCon);
                        container.getSubContainer().add(subCon);
                        containerHandler.editMainContainer(mainCon, container);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                    }else{
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation cannot be executed because §eone of the two§7 was not §efound");

                    }
                }else {
                    sendHelp(command);
                }

            }else if (args[0].equalsIgnoreCase("removeFromMain")){
                if(args.length == 3){
                    ContainerHandler containerHandler = new ContainerHandler();
                    String mainCon = args[1];
                    String subCon = args[2];
                    if(containerHandler.canFindeMainContainer(mainCon) && containerHandler.canFindeSubContainer(subCon)){
                        MainContainer container = containerHandler.getMainContainer(mainCon);
                        container.getSubContainer().remove(subCon);
                        containerHandler.editMainContainer(mainCon, container);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation was §esuccessful");
                    }else {
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "this operation cannot be executed because §eone of the two§7 was not §efound");
                    }
                }else {
                    sendHelp(command);
                }

            }else if (args[0].equalsIgnoreCase("listsub")) {
                ContainerHandler containerHandler = new ContainerHandler();
                String aliases = "";
                for (int i = 0; i !=   containerHandler.getSubContainersNames().size(); i++){
                    if(i == 0){
                        aliases =   "§e"+containerHandler.getSubContainersNames().get(i);
                    }else{
                        aliases = aliases + "§7, §e" +    containerHandler.getSubContainersNames().get(i);
                    }
                }

                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "the following §esubcontainers§7 were §efound§7:");
                if(aliases.equalsIgnoreCase("")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "subcontainers: §eNo Containers was Found");
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "subcontainers: " + aliases);
                }




            }else if (args[0].equalsIgnoreCase("listmain")) {
                ContainerHandler containerHandler = new ContainerHandler();
                String aliases = "";
                for (int i = 0; i !=   containerHandler.getMainContainersNames().size(); i++){
                    if(i == 0){

                        String subali = "";

                        for (int i2 = 0; i2 !=   containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().size(); i2++){
                            if(i2 == 0){
                                subali =   "§6"+containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().get(i2);
                            }else{
                                subali = subali +"§7, §6" + containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().get(i2);
                            }
                        }

                        aliases =   "§e"+containerHandler.getMainContainersNames().get(i) + "§7(§6"+subali+"§7)";
                    }else{

                        String subali = "";

                        for (int i2 = 0; i2 !=   containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().size(); i2++){
                            if(i2 == 0){
                                subali =   "§6"+containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().get(i2);
                            }else{
                                subali = subali +"§7, §6" + containerHandler.getMainContainer(containerHandler.getMainContainersNames().get(i)).getSubContainer().get(i2);
                            }
                        }

                        aliases = aliases +"§7, §e" +    containerHandler.getMainContainersNames().get(i) + "§7(§6"+subali+"§7)";
                    }
                }

                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "the following §emaincontainers §7were §efound§7:");

                if(aliases.equalsIgnoreCase("")){
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "maincontainers: §eNo Containers was Found");
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "maincontainers: " + aliases);
                }
            }else {
                sendHelp(command);
            }
        }
        return false;
    }

    private void sendHelp(ICommand command){
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "The following Commands are registered:");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " create <sub/main> §7~ create a new container or container group");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " listmain §7~ lists all maincontainers that exist");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " listsub §7~ lists all subcontainers that exist");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " delSub <containername> §7~ delete an existing subcontainer");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " delMain <containername>§7 ~ delete an existing subcontainer");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " editSub <containername> <key> <value> §7~ Edit a Subcontainer");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " editSubTypes §7~ shows everything you can edit on a subcontainer");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " addToMain <maincontainername> <subContainerName>§7 ~ add a subcontainer to a maincontainer");
        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.INFORMATION,true, "   -> §e" + command.getName() + " removeFromMain <maincontainername> <subcontainername> §7~ remove a subcontainer from a maincontainer ");

    }
}
