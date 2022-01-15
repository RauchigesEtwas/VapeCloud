package de.vapecloud.driver.events.events.clouderror;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/11:30
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.driver.events.EventHandler;
import de.vapecloud.driver.events.bin.EventListener;
import de.vapecloud.driver.events.bin.EventProvider;
import de.vapecloud.driver.events.bin.IEvent;

import java.lang.reflect.Method;

public class CloudErrorEvent implements IEvent {

    private String exception;
    private String exceptionMessage;

    public CloudErrorEvent(String exception, String exceptionMessage) {
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
    }

    public String getException() {
        return exception;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
