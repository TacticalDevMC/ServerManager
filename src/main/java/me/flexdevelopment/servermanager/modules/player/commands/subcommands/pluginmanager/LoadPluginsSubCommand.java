package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import me.flexdevelopment.servermanager.api.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LoadPluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        Plugin target = PluginUtil.getPluginByName(args, 1);
        String name = StringUtil.consolidateStrings(args, 1);

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        if (target != null) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginAlreadyLoaded().replace("%plugin%", target.getName()));
            return false;
        }

        if (PluginUtil.isIgnored(name)) {
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getPluginIgnored());
            return false;
        }

        sender.sendMessage(PluginUtil.load(name));
        return false;
    }
}
