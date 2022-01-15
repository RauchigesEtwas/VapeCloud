package de.vapecloud.api.bungeecord.handlers;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/15:14
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.api.bungeecord.BungeeAPILaunch;
import de.vapecloud.api.bungeecord.utils.serverhelper.ServerHelper;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Random;

public class CloudPlayerEnterNetworkListener implements Listener {

    @EventHandler
    public void enterNetwork(PostLoginEvent event){
        final ProxiedPlayer player = event.getPlayer();

        if (BungeeAPILaunch.getInstance().Lobbys.isEmpty()){

            ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
            player.disconnect(config.getMessages().get("cloud-proxy-no-fallback-found-kick"));
        } else {
            String lobby = BungeeAPILaunch.getInstance().Lobbys.get(new Random().nextInt(BungeeAPILaunch.getInstance().Lobbys.size()));
            player.connect(ServerHelper.getServerInfo(lobby));
        }

    }
}