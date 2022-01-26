package de.vapecloud.vapenet.handlers.listener;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:16
 * Created by Robin B. (RauchigesEtwas)
 */

import de.vapecloud.vapenet.handlers.bin.IPacketEvent;

public class NetworkExceptionEvent implements IPacketEvent {

    private Exception exception;

    public NetworkExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
