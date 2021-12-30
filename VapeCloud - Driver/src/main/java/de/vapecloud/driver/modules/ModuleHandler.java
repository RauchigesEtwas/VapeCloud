package de.vapecloud.driver.modules;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/21:31
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ModuleHandler {

    private ArrayList<String> loadedModules;

    public ArrayList<String> getLoadedModules() {
        return loadedModules;
    }

    public ModuleHandler() {
        this.loadedModules = new ArrayList<>();
    }

    public void loadModules(){
        ArrayList<String> modules = getModules();

        if (modules.isEmpty()){
            VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.MODULE, false, "no §eModule§7 was §efound");
            return;
        }

        modules.forEach(s -> {
            loadedModules.add(s);
            new ModuleLoader(s).moduleLoad();
        });
    }

    public void unloadModules(){
       loadedModules.forEach(s -> {
           new ModuleLoader(s).moduleUnload();
       });
    }
    public void reloadModules(){
        loadedModules.forEach(s -> {
            new ModuleLoader(s).moduleReload();
        });
    }



    private ArrayList<String> getModules() {
        File file = new File("./modules/");
        File[] files = file.listFiles();
        ArrayList<String> modules = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String FirstFilter = files[i].getName();
            String group = FirstFilter.split(".jar")[0];
            modules.add(group);
        }
        return modules;
    }
}
