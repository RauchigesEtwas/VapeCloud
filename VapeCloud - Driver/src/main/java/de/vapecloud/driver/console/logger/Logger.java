package de.vapecloud.driver.console.logger;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.Color;
import de.vapecloud.driver.console.logger.enums.MessageType;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class Logger {

    public ConsoleReader consoleReader;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm:ss" );




    /**
     * Instantiates a new Logger provider.
     */
    public Logger() {
        try {
            this.consoleReader = new ConsoleReader(System.in, System.out);
        } catch (IOException ignored) { }
        this.consoleReader.setExpandEvents(false);
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public void sendMessage(MessageType messageType, boolean usecommand, String message ) {
        if (messageType == MessageType.INFORMATION){
            printLine(usecommand,"INFO", message);
        }else if (messageType == MessageType.SETUP){
            printLine(usecommand,"SETUP", message);
        }else if (messageType == MessageType.NETWORK){
            printLine(usecommand,"NETWORK", message);
        }else if (messageType == MessageType.EMPTY){
            printLine(usecommand,null, message);
        }else if (messageType == MessageType.ERROR){
            printLine(usecommand,"§cERROR", message);
        }else if (messageType == MessageType.WARNING){
            printLine(usecommand,"§eWARN", message);
        }
    }




    private void printLine(Boolean usedcommand, String prefix, String message) {
        try {
            String inline = "";

            if(!usedcommand){
                inline = consoleReader.getCursorBuffer().toString();
                consoleReader.setPrompt("");
                consoleReader.resetPromptLine("", "", 0);
            }
            if(prefix == null){
                try {
                    consoleReader.println(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + colorString(message + Color.RESET.getAnsiCode()));
                    consoleReader.drawLine();
                    consoleReader.flush();

                } catch (IOException exception) {

                }
            }else{
                try {
                    consoleReader.println(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + colorString("§7[§f" + simpleDateFormat.format(System.currentTimeMillis()) +"§7] §b"+ prefix + "§7: §r" + Color.RESET.getAnsiCode() + message + Color.RESET.getAnsiCode()));

                    consoleReader.drawLine();
                    consoleReader.flush();
                } catch (IOException exception) {

                }
            }
            if(!usedcommand){

                String coloredPromp = colorString("§bVape§fCloud §7> §7");
                consoleReader.setPrompt(colorString(coloredPromp));
                consoleReader.resetPromptLine(colorString(coloredPromp), inline, inline.length());

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    /**
     * Read line string.
     *
     * @return the string
     */
    public String readLine() {
        try {
            return this.consoleReader.readLine();
        } catch (IOException ex) {
            return "null";
        }
    }



    /**
     * Gets console reader.
     *
     * @return the console reader
     */
    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }


    /**
     * Color string string.
     *
     * @param text the text
     * @return the string
     */
    public String colorString(String text) {

        for (Color consoleColour : Color.values()) {
            text = text.replace('§' + "" + consoleColour.getIndex(), consoleColour.getAnsiCode());
        }

        return text;
    }
}