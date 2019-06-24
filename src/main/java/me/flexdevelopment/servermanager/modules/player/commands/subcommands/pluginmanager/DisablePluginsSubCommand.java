package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisablePluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
        Plugin target = PluginUtil.getPluginByName(args, 1);

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
            PluginUtil.disableAll();
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getAllPluginsDisabled());
            return false;
        }

        if (target != null) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginNotExists().replace("%plugin%", target.getName()));
            return false;
        }

        if (!target.isEnabled()) {
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getPluginIsNotEnabled());
            return false;
        }

        PluginUtil.disable(plugin);
        player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginDisabled().replace("%plugin%", plugin.getName()));
        return false;
    }
}
