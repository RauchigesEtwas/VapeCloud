package de.vapecloud.driver.console;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.commandsystem.ICommandHandler;
import de.vapecloud.driver.commandsystem.ICommandSender;

import de.vapecloud.driver.console.logger.Logger;
import lombok.SneakyThrows;
import java.io.IOException;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class ConsolHandler extends Thread{

    private ICommandHandler commandHandler;
    private Logger logger;
    private String sender;

    public ConsolHandler() {}

    @SneakyThrows
    @Override
    public void run() {
        while (!isInterrupted() && isAlive()){
            if (VapeDriver.inSetup){

            }else{
                if(this.commandHandler != null){
                    String line;
                    String coloredPromp = this.getLogger().colorString("§bVape§fCloud §7» §7");
                    while ((line = this.getLogger().getConsoleReader().readLine(coloredPromp)) != null) {
                        if (!line.trim().isEmpty()) {
                            this.getLogger().getConsoleReader().resetPromptLine("", "", 0);
                            this.getLogger().getConsoleReader().setPrompt("");
                            this.getCommandHandler().executeCommand(line, new ICommandSender("console", sender, null, null));
                        }
                    }
                }
            }
        }
    }


    /**
     * Clear screen.
     */
    public void clearScreen(){
        try {
            this.getLogger().getConsoleReader().clearScreen();
        } catch (IOException exception) {
            this.getLogger().error(false, exception.getMessage());
        }
    }



    public String readLine() {
        return this.getLogger().readLine();
    }

    public void createHandel(String sender){
        this.initLogger();
        this.initCommandSystem();
        this.setDaemon(true);
        this.sender = sender;
        this.start();

    }

    public Logger getLogger() {
        return logger;
    }


    public ICommandHandler getCommandHandler() {
        return commandHandler;
    }

    public void closeHandel(){

        this.stop();
        this.destroy();
    }



    private void initLogger(){
        this.logger = new Logger();
    }

    private void initCommandSystem(){
        this.commandHandler = new ICommandHandler();
    }
}