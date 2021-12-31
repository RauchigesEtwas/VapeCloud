package de.vapecloud.driver.networking.client;

/*
 * Projectname: VapeCloud
 * Created AT: 03.01.2022/11:37
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.VapeDriver;
import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.vapenet.handlers.PacketListener;

public class LostConnectionHandler extends PacketListener {

    @Override
    public void handleException(Exception exception) {
        if (exception.getMessage().equalsIgnoreCase("Connection reset")){
            if (VapeDriver.getInstance().getVapeSettings().isRunningaCluster()){
                VapeDriver.getInstance().getConsolHandler().getLogger().sendMessage(MessageType.NETWORK, false, "The cluster has lost the connection to the manager");
            }else {
                System.out.println("NETWORK | Lost Connection!!!!!");
            }
        }
    }
}
