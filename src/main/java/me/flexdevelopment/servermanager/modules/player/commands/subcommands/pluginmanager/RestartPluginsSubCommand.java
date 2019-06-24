package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RestartPluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        Plugin target = PluginUtil.getPluginByName(args, 1);

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
            PluginUtil.disableAll();
            PluginUtil.enableAll();
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getAllPluginsRestarted());
            return false;
        }

        if (PluginUtil.isIgnored(target)) {
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getPluginIgnored());
            return false;
        }

        PluginUtil.disable(target);
        PluginUtil.enable(target);

        sender.sendMessage(ServerManager.getInstance().getMessageModule().getPluginRestarted().replace("%plugin%", target.getName()));

        return false;
    }
}
