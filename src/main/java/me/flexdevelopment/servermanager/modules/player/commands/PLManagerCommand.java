package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import me.flexdevelopment.servermanager.modules.player.commands.subcommands.pluginmanager.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PLManagerCommand extends CommandBase {


    public PLManagerCommand() {
        super("pluginmanager", Commands.PLMANAGERCOMMAND.getPermission());
    }

    // subcommands
    EnablePluginsSubCommand enable = new EnablePluginsSubCommand();
    LoadPluginsSubCommand load = new LoadPluginsSubCommand();
    UnloadPluginsSubCommand unload = new UnloadPluginsSubCommand();
    RestartPluginsSubCommand restart = new RestartPluginsSubCommand();
    ReloadPluginsSubCommand reload = new ReloadPluginsSubCommand();
    DisablePluginsSubCommand disable = new DisablePluginsSubCommand();
    DumpPluginsSubCommand dump = new DumpPluginsSubCommand();
    InfoPluginsSubCommand info = new InfoPluginsSubCommand();
    ListPluginsSubCommand list = new ListPluginsSubCommand();

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getPlManagerArgsNotFound());
            return false;
        }

        switch (args[0]) {
            case "enable":
                this.enable.onCommand(sender, cmd, label, args);
                break;
            case "load":
                this.load.onCommand(sender, cmd, label, args);
                break;
            case "unload":
                this.unload.onCommand(sender, cmd, label, args);
                break;
            case "restart":
                this.restart.onCommand(sender, cmd, label, args);
                break;
            case "reload":
                this.reload.onCommand(sender, cmd, label, args);
                break;
            case "disable":
                this.disable.onCommand(sender, cmd, label, args);
                break;
            case "dump":
                this.dump.onCommand(sender, cmd, label, args);
                break;
            case "info":
                this.info.onCommand(sender, cmd, label, args);
                break;
            case "list":
                this.list.onCommand(sender, cmd, label, args);
                break;
            default:
                player.sendMessage(ServerManager.getInstance().getMessageModule().getGeenSubCommandFound());

        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> returnList = new ArrayList<>();

        if (args.length == 1) {
            returnList.add("enable");
            returnList.add("disable");
            returnList.add("load");
            returnList.add("unload");
            returnList.add("restart");
            returnList.add("reload");
            returnList.add("disable");
            returnList.add("dump");
            returnList.add("info");
            returnList.add("list");
        }

        if (args.length == 2) {
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                returnList.add(plugin.getName());
            }
        }
        return returnList;
    }
}
