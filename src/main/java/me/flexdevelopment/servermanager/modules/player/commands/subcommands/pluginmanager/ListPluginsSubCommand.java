package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FlagUtil;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import me.flexdevelopment.servermanager.inventory.menus.PluginsListMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;

public class ListPluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        boolean includedVersions = FlagUtil.hasFlag(args, 'v');

        List<String> pluginList = Lists.newArrayList();

        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            pluginList.add(PluginUtil.getFormattedName(plugin, includedVersions));
        }

        Collections.sort(pluginList, String.CASE_INSENSITIVE_ORDER);

        String plugins = Joiner.on(", ").join(pluginList);

        player.sendMessage(ServerManager.getInstance().getMessageModule().getListHeader().replace("%totalPlugins%", String.valueOf(pluginList.size())).replace("%plugins%", plugins));

        PluginsListMenu pluginsListMenu = new PluginsListMenu();

        pluginsListMenu.open(player);

        return false;
    }
}
