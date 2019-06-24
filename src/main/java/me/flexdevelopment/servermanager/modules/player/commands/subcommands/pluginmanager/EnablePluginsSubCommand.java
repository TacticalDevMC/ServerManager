package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import me.flexdevelopment.servermanager.api.utils.PluginsManager;
import me.flexdevelopment.servermanager.api.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class EnablePluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
        Plugin target = PluginUtil.getPluginByName(args, 1);

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        if (target != null) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginNotExists().replace("%plugin%", target.getName()));
            return false;
        }

        if (PluginsManager.isPluginEnabled(plugin)) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginAlreadyEnabled());
            return false;
        }

        PluginUtil.enable(plugin);
        player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginEnabled().replace("%plugin%", plugin.getName()));

        return false;
    }
}
