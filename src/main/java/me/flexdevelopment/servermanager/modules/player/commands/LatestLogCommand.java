package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class LatestLogCommand extends CommandBase {


    public LatestLogCommand() {
        super("latestlog", Commands.LATESTLOGCOMMAND.getPermission());
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.LatestLog.geenArgs"));
            return false;
        }

        switch (args[0]) {
            case "message":
//                if (args.length == 1) {
//                    List<String> logs = FileManager.get("logs/message.yml").getStringList("Players.");
//
////                    if (logs.isEmpty()) {
////                        player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.LatestLog.geenLogsGevonden"));
////                        return false;
////                    }
//
//                    for (String logsmsg : logs) {
//                        player.sendMessage(color("&7---------------------------"));
//                        player.sendMessage(logsmsg);
//                        player.sendMessage(color("&7---------------------------"));
//                    }
//                } else {

                if (args.length == 1) {
                    player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.LatestLog.geenArgs"));
                    return false;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                List<String> logs = FileManager.get("logs/message.yml").getStringList("Players." + target.getName());
                
                if (logs == null) {
                    player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.LatestLog.geenLogsGevonden"));
                    return false;
                }

                player.sendMessage(ServerManager.getInstance().getMessageManager().replaceConfigMessage("Commands.LatestLog.info.header", "%target%", target.getName()));
                for (String logsmsg : logs) {
                    player.sendMessage(ServerManager.getInstance().getMessageManager().replaceConfigMessage("Commands.LatestLog.info.messageFromAuthor", "%message%", logsmsg));
                }
                player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.LatestLog.info.footer"));
                break;
            case "commands":
                player.sendMessage(color("&cDe log &6'commands' &ckomt in één van de volgende updates."));
                break;
            case "functions":
                player.sendMessage(color("&cDe log &6'functions' &ckomt in één van de volgende updates."));
                break;
            default:
                player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Default.subCommandNotFound"));
        }

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> returnList = new ArrayList<>();

        if (args.length == 1) {
            returnList.add("message");
            returnList.add("commands");
            returnList.add("functions");
        }

        if (args.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                returnList.add(player.getName());
            }
        }

        return returnList;
    }
}
