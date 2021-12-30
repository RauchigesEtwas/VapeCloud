package de.vapecloud.driver.container.containers;

/*
 * Projectname: VapeCloud
 * Created AT: 30.12.2021/11:33
 * Created by Robin B. (RauchigesEtwas)
 */

import java.util.ArrayList;

public class MainContainer {


    private ArrayList<String> subContainer;

    public MainContainer() {
        this.subContainer = new ArrayList<>();
    }

    public ArrayList<String> getSubContainer() {
        return subContainer;
    }
}
