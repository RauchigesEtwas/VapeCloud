package de.vapecloud.launcher.manager.events;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/11:42
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.events.bin.EventListener;
import de.vapecloud.driver.events.bin.EventProvider;
import de.vapecloud.driver.events.events.clouderror.CloudErrorEvent;

public class CloudErrorListener implements EventListener {

    @EventProvider(priority = 200)
    public void cloudErrorEvent(CloudErrorEvent event){
    }


}
