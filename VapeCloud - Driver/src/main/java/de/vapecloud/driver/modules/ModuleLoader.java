package de.vapecloud.driver.modules;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/21:33
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.modules.interfaces.IModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleLoader {

    private File file;
    private String modulename;

    public ModuleLoader(String name) {

        this.file = new File("./modules/" + name + ".jar");
        this.modulename = name;

    }

    public Properties moduleLoad() {

        if (this.file == null){
            return null;
        }

        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
            try (JarFile jarFile = new JarFile(file)) {
                JarEntry jarEntry = jarFile.getJarEntry("module.properties");
                if (jarEntry != null){
                    try (InputStreamReader reader = new InputStreamReader(jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8)) {
                        Properties properties = new Properties();
                        properties.load(reader);



                        Class classtoLoad = Class.forName(properties.getProperty("main"), true, classLoader);
                        Method method = classtoLoad.getDeclaredMethod("moduleLoad");
                        Object instance = classtoLoad.newInstance();
                        Object resuls = method.invoke(instance);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "module §e"+properties.getProperty("name")+" §7was §aloaded §7[Author: §e"+properties.getProperty("author")+"§7, Version: §e"+properties.getProperty("version")+"§7]");

                        return properties;
                    }catch (Exception ignored){}

                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "No §emodule.properties §7found");
                }

            }catch (Exception ignored){}
        }catch (Exception e){

        }
        return null;
    }

    public void moduleUnload() {

        if (this.file == null){
            return;
        }

        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
            try (JarFile jarFile = new JarFile(file)) {
                JarEntry jarEntry = jarFile.getJarEntry("module.properties");
                if (jarEntry != null){
                    try (InputStreamReader reader = new InputStreamReader(jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8)) {
                        Properties properties = new Properties();
                        properties.load(reader);



                        Class classtoLoad = Class.forName(properties.getProperty("main"), true, classLoader);
                        Method method = classtoLoad.getDeclaredMethod("moduleUnload");
                        Object instance = classtoLoad.newInstance();
                        Object resuls = method.invoke(instance);

                    }catch (Exception ignored){}

                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "No §emodule.properties §7found");
                }

            }catch (Exception ignored){}
        }catch (Exception e){

        }
    }

    public void moduleReload() {

        if (this.file == null){
            return;
        }

        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
            try (JarFile jarFile = new JarFile(file)) {
                JarEntry jarEntry = jarFile.getJarEntry("module.properties");
                if (jarEntry != null){
                    try (InputStreamReader reader = new InputStreamReader(jarFile.getInputStream(jarEntry), StandardCharsets.UTF_8)) {
                        Properties properties = new Properties();
                        properties.load(reader);



                        Class classtoLoad = Class.forName(properties.getProperty("main"), true, classLoader);
                        Method method = classtoLoad.getDeclaredMethod("moduleReload");
                        Object instance = classtoLoad.newInstance();
                        Object resuls = method.invoke(instance);
                        VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "module §e"+properties.getProperty("name")+" §7was reloaded");

                    }catch (Exception ignored){}

                }else {
                    VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "No §emodule.properties §7found (Jar-Name: §e"+this.modulename+"§7)");
                }

            }catch (Exception ignored){}
        }catch (Exception e){

        }
    }
}
