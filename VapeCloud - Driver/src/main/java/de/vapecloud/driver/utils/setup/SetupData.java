package de.vapecloud.driver.utils.setup;

/*
 * Projectname: VapeCloud
 * Created AT: 27.12.2021/15:16
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.console.logger.enums.MessageType;
import de.vapecloud.driver.container.ContainerHandler;

import java.util.HashMap;

public class SetupData {

    public SetupData() {}

    public boolean inSetup = false;
    public int setupStep = 0;
    public SetupTypes setupTypes;
    public ContainerHandler containerHandler;
    public HashMap<String, String> setupMemory;
    public HashMap<MessageType, String> memoryMessage;
}
