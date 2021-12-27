package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 26.12.2021/16:30
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.utils.setup.SetupData;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class VapeSettings {

    public VapeSettings() {}

    @Getter private DataCenter dataCenter = new DataCenter();
    @Getter private SetupData setupData = new SetupData();
    @Getter @Setter private boolean runningaCluster = false;
    @Getter @Setter private String cloudVersion = "Earthquake-1.0.0";


}
