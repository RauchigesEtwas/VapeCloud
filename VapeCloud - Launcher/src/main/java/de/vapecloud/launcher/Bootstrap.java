package de.vapecloud.launcher;

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.console.ConsolHandler;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.utils.setup.SetupTypes;
import de.vapecloud.launcher.manager.VapeManager;
import lombok.SneakyThrows;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class Bootstrap {


    @SneakyThrows
    public static void main(String[] args) {
        if (VapeDriver.consolHandler == null){
            VapeDriver.consolHandler = new ConsolHandler();
            VapeDriver.consolHandler.createHandel("CONSOLE");
        }

        InitLibs();

        VapeDriver.consolHandler.clearScreen();
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.EMPTY,false, VapeDriver.dataCenter.getLogo());

        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "New instance of the VapeCloud will be launched.....");
        Thread.sleep(50);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "we look if the cloud is already setup....");
        Thread.sleep(50);
        if(!new ConfigHandler("service.json").canFinde()){
            VapeDriver.consolHandler.getLogger().sendMessage(MessageType.SETUP,false, "Oh no the cloud still needs to be setup§7.");
            Thread.sleep(50);
            VapeDriver.consolHandler.getLogger().sendMessage(MessageType.SETUP,false, "A small setup will now be executed");

            VapeDriver.vapeSettings.getSetupData().setupTypes = SetupTypes.STARTUP;
            VapeDriver.vapeSettings.getSetupData().inSetup = true;
            VapeDriver.vapeSettings.getSetupData().setupMemory.clear();

        }else{
            VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "The cloud is ready for takeoff");
        }

        new VapeManager().initVapeManager();
    }


    @SneakyThrows
    private static void InitLibs(){
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Loading Libraries....");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.channel.ChannelHandlerContext");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.channel.SimpleChannelInboundHandler");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.bootstrap.ServerBootstrap");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.bootstrap.Bootstrap");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.channel.epoll.Epoll.*");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.channel.socket.nio.NioSocketChannel");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: NETTY -> io.netty.handler.codec.*");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: JLINE -> jline.console.ConsoleReade");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: GSON -> com.google.gson.Gson");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(10);
        VapeDriver.consolHandler.getLogger().sendMessage(MessageType.INFORMATION,false, "Init from: GSON -> com.google.gson.GsonBuilder");
        VapeDriver.consolHandler.getLogger().getConsoleReader().resetPromptLine("", "", 0);
        VapeDriver.consolHandler.getLogger().getConsoleReader().setPrompt("");
        Thread.sleep(500);
    }
}
