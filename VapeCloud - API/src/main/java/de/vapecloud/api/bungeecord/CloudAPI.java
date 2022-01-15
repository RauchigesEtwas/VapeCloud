package de.vapecloud.api.bungeecord;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/12:55
 * Created by Robin B. (RauchigesEtwas)
 */

public class CloudAPI {
    private static CloudAPI instance;

    public CloudAPI() {
        instance = this;
    }

    public static CloudAPI getInstance() {
        return instance;
    }
}
