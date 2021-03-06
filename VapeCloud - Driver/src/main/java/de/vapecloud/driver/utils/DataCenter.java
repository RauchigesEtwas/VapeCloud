package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ThreadLocalRandom;

public class DataCenter {



    /**
     * Return the Logo with an String
     */
    public String getLogo(){
        return  "                                                           \n" +
                "           §b_  _ ____ ___  ____ §f____ _    ____ _  _ ___   \n" +
                "           §b|  | |__| |__] |___ §f|    |    |  | |  | |  \\ \n" +
                "            §b\\/  |  | |    |___ §f|___ |___ |__| |__| |__/ \n" +
                "      _______________________________________________________\n" +
                "    Always §bready§f for §bsomthing§f new §7- §bhttps://discord.gg/4kKEcaP9W\n";
    }



    /**
     * bungee configuration to load them easily
     */
    public String getBungeeConfig(int port, int maxPlayers){
        return "player_limit: "+maxPlayers+"\n" +
                "ip_forward: true\n" +
                "permissions:\n" +
                "timeout: 30000\n" +
                "log_commands: false\n" +
                "online_mode: true\n" +
                "servers:\n" +
                "listeners:\n" +
                "- query_port: "+port+"\n" +
                "  motd: \"§8| §bVapeCLoud §8- §7Proxy Service\"\n" +
                "  priorities:\n" +
                "  bind_local_address: true\n" +
                "  tab_list: GLOBAL_PING\n" +
                "  query_enabled: false\n" +
                "  host: 0.0.0.0:"+port+"\n" +
                "  forced_hosts:\n" +
                "    pvp.md-5.net: pvp\n" +
                "  max_players: "+maxPlayers+"\n" +
                "  tab_size: 120\n" +
                "  ping_passthrough: false\n" +
                "  force_default_server: false\n" +
                "disabled_commands:\n" +
                "- disabledcommandhere\n" +
                "network_compression_threshold: 256\n" +
                "groups:\n" +
                "connection_throttle: 4000\n" +
                "stats: 010ba0aa-95f1-4709-8345-dd88f9e2d4bf\n";
    }


    /**
     * bungee configuration to load them easily
     */
    public String getSpigotConfigs(){
        return "# This is the main configuration file for Bukkit.\n" +
                "# As you can see, there's actually not that much to configure without any plugins.\n" +
                "# For a reference for any variable inside this file, check out the Bukkit Wiki at\n" +
                "# http://wiki.bukkit.org/Bukkit.yml\n" +
                "# \n" +
                "# If you need help on this file, feel free to join us on irc or leave a message\n" +
                "# on the forums asking for advice.\n" +
                "# \n" +
                "# IRC: #spigot @ irc.spi.gt\n" +
                "#    (If this means nothing to you, just go to http://www.spigotmc.org/pages/irc/ )\n" +
                "# Forums: http://www.spigotmc.org/\n" +
                "# Bug tracker: http://www.spigotmc.org/go/bugs\n" +
                "\n" +
                "\n" +
                "settings:\n" +
                "  allow-end: true\n" +
                "  warn-on-overload: true\n" +
                "  permissions-file: permissions.yml\n" +
                "  update-folder: update\n" +
                "  plugin-profiling: false\n" +
                "  connection-throttle: 0\n" +
                "  query-plugins: true\n" +
                "  deprecated-verbose: default\n" +
                "  shutdown-message: Server closed\n" +
                "spawn-limits:\n" +
                "  monsters: 70\n" +
                "  animals: 15\n" +
                "  water-animals: 5\n" +
                "  ambient: 15\n" +
                "chunk-gc:\n" +
                "  period-in-ticks: 600\n" +
                "  load-threshold: 0\n" +
                "ticks-per:\n" +
                "  animal-spawns: 400\n" +
                "  monster-spawns: 1\n" +
                "  autosave: 6000\n" +
                "aliases: now-in-commands.yml\n" +
                "database:\n" +
                "  username: bukkit\n" +
                "  isolation: SERIALIZABLE\n" +
                "  driver: org.sqlite.JDBC\n" +
                "  password: walrus\n" +
                "  url: jdbc:sqlite:{DIR}{NAME}.db\n";
    }



    /**
     * spigot configuration to load them easily
     */
    public String getSpigotConfig(){
        return "#Minecraft server properties\n" +
                "#Mon Jan 25 10:33:48 CET 2021\n" +
                "spawn-protection=0\n" +
                "generator-settings=\n" +
                "force-gamemode=false\n" +
                "allow-nether=true\n" +
                "gamemode=0\n" +
                "broadcast-console-to-ops=true\n" +
                "enable-query=false\n" +
                "player-idle-timeout=0\n" +
                "difficulty=1\n" +
                "spawn-monsters=true\n" +
                "op-permission-level=0\n" +
                "resource-pack-hash=\n" +
                "announce-player-achievements=true\n" +
                "pvp=true\n" +
                "snooper-enabled=true\n" +
                "level-type=DEFAULT\n" +
                "hardcore=false\n" +
                "enable-command-block=false\n" +
                "max-players=\n" +
                "network-compression-threshold=256\n" +
                "max-world-size=29999984\n" +
                "server-port=\n" +
                "debug=false\n" +
                "server-ip=\n" +
                "spawn-npcs=true\n" +
                "allow-flight=false\n" +
                "level-name=world\n" +
                "view-distance=10\n" +
                "resource-pack=\n" +
                "spawn-animals=true\n" +
                "white-list=false\n" +
                "generate-structures=true\n" +
                "online-mode=false\n" +
                "max-build-height=256\n" +
                "level-seed=\n" +
                "enable-rcon=false\n" +
                "motd=\"VapeCloud - Service\"\n";
    }

    public String[] dropFirstString(String[] input){
        String[] astring = new String[input.length - 1];
        System.arraycopy(input, 1, astring, 0, input.length - 1);
        return astring;
    }



}
