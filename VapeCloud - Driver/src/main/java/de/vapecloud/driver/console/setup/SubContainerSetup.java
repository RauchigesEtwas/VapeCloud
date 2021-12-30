package de.vapecloud.driver.console.setup;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/14:45
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.containers.SubContainer;
import de.vapecloud.driver.container.enums.ContainerModeType;
import de.vapecloud.driver.container.enums.ContainerVersion;
import de.vapecloud.driver.utils.download.Downloader;

import java.io.File;
import java.io.IOException;

public class SubContainerSetup {

    public SubContainerSetup(String line) throws IOException {

        if (line.equalsIgnoreCase("cancel")){
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the setup was §esuccessfully aborted§7 you can now use §eall commands§7 again");
            VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
            VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
            VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = false;
            VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep = 0;
            VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.forEach((messageType, s) -> VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(messageType, true, s));
        return;
        }


        switch (VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep){
            case 0:
                if(!VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler.canFindeSubContainer(line)){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("CONTAINER", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "how many §eServer§7 should always be §eonline§7?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the subcontainer §ealready exists§7 but you can now §especify a new name");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 1:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("TOTALONLINE", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "how many may §eServer §7be online at most ( §e-1 §7= §eInfinite§7)");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may §eonly§7 contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 2:
                if(line.matches("[0-9]+") || line.equalsIgnoreCase("-1")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("MAXIMALONLINE", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the §eminimum memory§7 required for the server §eto start§7?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may §eonly§7 contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 3:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("MINIMALMEMORY", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what is the §emaximum amount§7 of memory the server can have to start?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may §eonly§7 contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 4:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("MAXIMALMEMORY", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "how many §eplayers §7are allowed on one server?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may §eonly§7 contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 5:
                if(line.matches("[0-9]+")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("MAXIMALPLAYERS", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "should the container be §estatic§7?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "Your input may §eonly §7contain numbers");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 6:
                if(line.equalsIgnoreCase("true") || line.equalsIgnoreCase("false")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("STATICSERVICE", line);
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "what kind of server is this? (Types: §eLOBBY§7, §ePROXY§7, §eGAME§7)");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "please enter §etrue§7 or §efalse");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 7:
                if(line.equalsIgnoreCase("lobby") || line.equalsIgnoreCase("proxy") || line.equalsIgnoreCase("game") ){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("CONTAINERMODETYPE", line);
                    if (line.equalsIgnoreCase("lobby") || line.equalsIgnoreCase("game")){
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "which §esoftware§7 do you want to use?)");
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "Versions: §ePAPERSPIGOT_1_8_8§7, §ePAPERSPIGOT_1_9_4§7, §ePAPERSPIGOT_1_10_2§7, §ePAPERSPIGOT_1_11_2§7, §ePAPERSPIGOT_1_12_2§7, §ePAPERSPIGOT_1_13_2§7, §ePAPERSPIGOT_1_14_4§7, §ePAPERSPIGOT_1_15_2§7, §ePAPERSPIGOT_1_16_5§7, §ePAPERSPIGOT_1_17_1§7, §ePAPERSPIGOT_1_18_1");
                        VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                        VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    }else {
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "which §esoftware§7 do you want to use?");
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "Versions: §eBUNGEECORD_LATEST§7, §eWATERFALL_LATEST");
                        VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                        VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    }
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else{
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "please specify one of the Types Types: §eLOBBY§7, §ePROXY§7, §eGAME§7)");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 8:
                if (line.equalsIgnoreCase("BUNGEECORD_LATEST") || line.equalsIgnoreCase("WATERFALL_LATEST")  || line.equalsIgnoreCase("PAPERSPIGOT_1_8_8") || line.equalsIgnoreCase("PAPERSPIGOT_1_9_4") || line.equalsIgnoreCase("PAPERSPIGOT_1_10_2") || line.equalsIgnoreCase("PAPERSPIGOT_1_11_2") || line.equalsIgnoreCase("PAPERSPIGOT_1_12_2")
                || line.equalsIgnoreCase("PAPERSPIGOT_1_13_2") || line.equalsIgnoreCase("PAPERSPIGOT_1_14_4") || line.equalsIgnoreCase("PAPERSPIGOT_1_15_2") || line.equalsIgnoreCase("PAPERSPIGOT_1_16_5") || line.equalsIgnoreCase("PAPERSPIGOT_1_17_1") || line.equalsIgnoreCase("PAPERSPIGOT_1_18_1")){
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("CONTAINERVERSION", line.toUpperCase());
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "on which §eCluster §7should the containers §estart§7?");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                    VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep++;
                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "please §echoose a right§7 version!");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                    VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                }
                break;
            case 9:
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.put("CLUSTER", line);


                SubContainer container = new SubContainer(
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINER"),
                        Integer.parseInt(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("TOTALONLINE")),
                        Integer.parseInt(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("MAXIMALONLINE")),
                        Integer.parseInt(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("MINIMALMEMORY")),
                        Integer.parseInt(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("MAXIMALMEMORY")),
                        Integer.parseInt(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("MAXIMALPLAYERS")),
                        true,
                        Boolean.parseBoolean(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("STATICSERVICE")),
                        null,
                        15000,
                        ContainerModeType.valueOf(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINERMODETYPE")),
                        ContainerVersion.valueOf(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINERVERSION")),
                        VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CLUSTER"));
                new File("./local/templates/" +    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINER") + "/plugins").mkdirs();


                new Downloader("server.jar", "./local/templates/" +    VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINER"), container.getContainerVersion());

                VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler.addSubContainer(VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.get("CONTAINER"), container);
                VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = false;
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep = 0;
                VapeDriver.getInstance().getVapeSettings().getSetupData().setupMemory.clear();
                VapeDriver.getInstance().getConsolHandler().clearScreen();
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.EMPTY,true, VapeDriver.getInstance().getVapeSettings().getDataCenter().getLogo());
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "the §econtainer§7 was created §esuccessfully");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);

                VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.forEach((messageType, s) -> VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(messageType, true, s));
                VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.clear();
        }
    }
}
