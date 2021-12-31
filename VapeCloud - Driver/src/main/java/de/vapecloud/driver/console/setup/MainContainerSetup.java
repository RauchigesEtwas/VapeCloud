package de.vapecloud.driver.console.setup;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/14:45
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.containers.MainContainer;

import java.io.IOException;
import java.util.function.BiConsumer;

public class MainContainerSetup {

    public MainContainerSetup(String line) throws IOException {

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
               String container = line;
               if (!VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler.canFindeMainContainer(container)){
                   VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler.addMainContainer(container, new MainContainer());
                   VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "you have §esuccessfully§7 created the §emaincontainer");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
                   VapeDriver.getInstance().getVapeSettings().getSetupData().inSetup = false;
                   VapeDriver.getInstance().getVapeSettings().getSetupData().setupStep = 0;
                   VapeDriver.getInstance().getConsolHandler().clearScreen();
                   VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.EMPTY,true, VapeDriver.getInstance().getVapeSettings().getDataCenter().getLogo());
                   VapeDriver.getInstance().getVapeSettings().getSetupData().containerHandler.addMainContainer(container, new MainContainer());
                   VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, false, "you have §esuccessfully§7 created the §emaincontainer");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);


                   VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.forEach((messageType, s) -> VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(messageType, true, s));
                   VapeDriver.getInstance().getVapeSettings().getSetupData().memoryMessage.clear();
               }else{
                   VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.SETUP, true, "the maincontainer §ealready exists§7 but you can now §especify a new name");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().setPrompt("");
                   VapeDriver.getInstance().getConsolHandler().getLogger().getConsoleReader().resetPromptLine("", "", 0);
               }

       }
    }
}
