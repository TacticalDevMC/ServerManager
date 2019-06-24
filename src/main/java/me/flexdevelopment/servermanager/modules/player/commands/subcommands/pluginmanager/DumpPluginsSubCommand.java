package me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class DumpPluginsSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        File dumpFile = new File(ServerManager.getInstance().getDataFolder(), "versions.txt");

        PrintWriter writer = null;

        List<String> plugins = PluginUtil.getPluginNames(true);
        Collections.sort(plugins, String.CASE_INSENSITIVE_ORDER);

        try {
            writer = new PrintWriter(dumpFile);
            for (String pluginDump : plugins)
                writer.println(pluginDump);
            player.sendMessage(ServerManager.getInstance().getMessageModule().getDumpOnFileName().replace("%dumpFile%", dumpFile.getName()));
        } catch (IOException e) {
            sender.sendMessage(ServerManager.getInstance().getMessageModule().getDumpError());
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }

        return false;
    }
}
