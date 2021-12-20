package de.vapecloud.driver.logger;

import de.vapecloud.driver.logger.enums.IColor;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;
import java.io.IOException;
import java.text.SimpleDateFormat;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class ILogger {

    public ConsoleReader consoleReader;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm:ss" );



    /**
     * Instantiates a new Logger provider.
     */
    public ILogger() {
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
    public void info(boolean usecommand, String message ) {
        printLine(usecommand,"INFO", message);
    }


    /**
     * SETUP.
     *
     * @param message the message
     */
    public void setup(boolean usecommand, String message ) {
        printLine(usecommand,"SETUP", message);
    }

    public void networking(boolean usecommand, String message) {
        printLine(usecommand,"NETWORK", message);
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public void error(boolean usecommand, String message) {
        printLine(usecommand,"§cERROR", "§c" + message);
    }

    public void empty(boolean usecommand, String message ) {
        printLine(usecommand,null, message);
    }


    /**
     * Warning.
     *
     * @param message the message
     */
    public void succecess(boolean usecommand, String message ) {
        printLine(usecommand,"§aSUCCESS", message);
    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public void warning(boolean usecommand, String message ) {
        printLine(usecommand,"§eWARN", message);
    }

    /**
     * Debug.
     *
     * @param message the message
     */
    public void debug(boolean usecommand, String message) {
        printLine(usecommand,
                "§9DEBUG", message);
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
                    consoleReader.println(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + colorString(message + IColor.RESET.getAnsiCode()));
                    consoleReader.drawLine();
                    consoleReader.flush();

                } catch (IOException exception) {

                }
            }else{
                try {
                    consoleReader.println(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + colorString("§7[§f" + simpleDateFormat.format(System.currentTimeMillis()) +"§7] §b"+ prefix + "§8: §7" + IColor.RESET.getAnsiCode() + message + IColor.RESET.getAnsiCode()));

                    consoleReader.drawLine();
                    consoleReader.flush();
                } catch (IOException exception) {

                }
            }
            if(!usedcommand){

                consoleReader.setPrompt(colorString("§bVapefCloud §7» §7"));
                consoleReader.resetPromptLine(colorString("§bVape§fCloud §7» §7"), inline, inline.length());

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

        for (IColor consoleColour : IColor.values()) {
            text = text.replace('§' + "" + consoleColour.getIndex(), consoleColour.getAnsiCode());
        }

        return text;
    }
}