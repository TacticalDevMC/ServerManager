package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import com.google.common.base.Joiner;
import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class InfoPluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        Plugin target = PluginUtil.getPluginByName(args, 1);


        if (target == null) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPluginNotExists().replace("%plugin%", target.getName()));
            return false;
        }

        String plName = target.getName();
        String version = target.getDescription().getVersion();
        String authors = target.getDescription().getAuthors().size() == 0 ? ChatColor.RED + "Geen" : Joiner.on(", ").join(target.getDescription().getAuthors());
        String status = target.isEnabled() ? ChatColor.GREEN + "Aan" : ChatColor.RED + "Uit";
        List<String> dependList = target.getDescription().getDepend();
        List<String> softdependList = target.getDescription().getSoftDepend();

        player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoHeader().replace("%pluginName%", plName));
        player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoVersion().replace("%pluginVersion%", version));
        player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoAuthors().replace("%pluginAuthors%", authors));
        player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoStatus().replace("%pluginStatus%", status));
        if (!dependList.isEmpty()) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoDepends().replace("%pluginDepends%", Joiner.on(", ").join(dependList)));
        }
        if (!softdependList.isEmpty()) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getInfoSoftDepends().replace("%pluginSoftDepends%", Joiner.on(", ").join(softdependList)));
        }

        return false;
    }
}
