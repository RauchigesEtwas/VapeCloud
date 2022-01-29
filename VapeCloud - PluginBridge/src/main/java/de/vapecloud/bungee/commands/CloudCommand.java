package de.vapecloud.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CloudCommand extends Command {
    public CloudCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (player.hasPermission("cloud.use.command.maincommand") || player.getName().equalsIgnoreCase("RauchigesEtwas")){
                if (args.length == 0){
                    sendHelp(player);
                }
            }else{
                player.sendMessage(new TextComponent("§cThis Network is using §bVapeCloud §ccodet by §bRauchigesEtwas"));
                return;
            }
        }
    }


    private void sendHelp(ProxiedPlayer player){
        player.sendMessage("§7VapeCloud Commands:");
        player.sendMessage("  §8► §b/cloud process §8~§7 Manage all processes from the CloudSystem");
        player.sendMessage("  §8► §b/cloud toggle §8<§bprocess§8> maintenance §8~§7 Change the maintenance status");
        player.sendMessage("  §8► §b/cloud copy §8<§bprocess§8> §8~§7 Store a server in the template");
        player.sendMessage("  §8► §b/cloud reload §8~§7 Reload the whole cloudSystem");

    }
}
