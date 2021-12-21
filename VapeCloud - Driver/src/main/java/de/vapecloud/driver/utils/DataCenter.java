package de.vapecloud.driver.utils;

/*
 * Projectname: VapeCloud
 * Created AT: 21.12.2021/15:06
 * Created by Robin B. (RauchigesEtwas)
 */

public class DataCenter {



    /**
     * Return the Logo with an String
     */
    public String getLogo(){
        return "\n" +
                "       _   __              _______             __\n" +
                "      | | / /__ ____  ___ / ___/ /__  __ _____/ /\n" +
                "      | |/ / _ `/ _ \\/ -_) /__/ / _ \\/ // / _  /\n" +
                "      |___/\\_,_/ .__/\\__/\\___/_/\\___/\\_,_/\\_,_/\n" +
                "              /_/\n" +
                "\n" +
                "  <!> Yalways ready for something new! | VERSION: Earthquake-V1.0.0\n" +
                "  <!> Do you want Support? https://discord.gg/4kKEcaP9WC";
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
                "online_mode: false\n" +
                "servers:\n" +
                "listeners:\n" +
                "- query_port: "+port+"\n" +
                "  motd: A UniqueCloud Service\n" +
                "  priorities:\n" +
                "  bind_local_address: true\n" +
                "  tab_list: GLOBAL_PING\n" +
                "  query_enabled: false\n" +
                "  host: 127.0.0.1:"+port+"\n" +
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
                "motd=A UniqueCloud Service\n";
    }

    public String[] dropFirstString(String[] input){
        String[] astring = new String[input.length - 1];
        System.arraycopy(input, 1, astring, 0, input.length - 1);
        return astring;
    }
}
