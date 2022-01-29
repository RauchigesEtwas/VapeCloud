package de.vapecloud.bungee.listener;

import de.vapecloud.bungee.BungeeBridge;
import de.vapecloud.bungee.utils.server.ServerManager;
import de.vapecloud.driver.configuration.ConfigHandler;
import de.vapecloud.driver.configuration.configs.ProcessConfig;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import java.util.Random;

public class CloudPlayerEnterNetworkListener implements Listener {

    @EventHandler
    public void enterNetwork(PostLoginEvent event){
        final ProxiedPlayer player = event.getPlayer();

        if (BungeeBridge.getInstance().getGeneralHandler().lobbys.isEmpty()){

            ProcessConfig config = (ProcessConfig) new ConfigHandler("./cloud/config.json").getConfig(ProcessConfig.class);
            player.disconnect(config.getMessages().get("cloud-proxy-no-fallback-found-kick"));
        } else {
            String lobby = BungeeBridge.getInstance().getGeneralHandler().lobbys.get(new Random().nextInt(BungeeBridge.getInstance().getGeneralHandler().lobbys.size()));
            player.connect(ServerManager.getServerInfo(lobby));
        }

    }
}
