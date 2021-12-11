package de.vapecloud.launcher;
/*
 * Created AT: 13.11.2021
 * Created by Robin B. (UniqueByte)
 */


import de.vapecloud.launcher.manager.VapeManager;

public class Bootstrap {


    public static void main(String[] args) {
        new VapeManager().InitVapeMaster();
    }
}
