package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.report.ReportType;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReportCommand extends CommandBase {

    public ReportCommand() {
        super("report");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getReportArgsNotFound());
            return false;
        }

        if (args.length == 1) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getReportArgsNotFound());
            return false;
        }

        if (args.length == 2) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getReportArgsNotFound());
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getGeenSpelerGevonden().replace("%target%", target.getName()));
            return false;
        }

        switch (args[1]) {
            case "Hackes":
                if (args[2].equalsIgnoreCase("speed")) {
                    ServerManager.getInstance().getReportManager().reportPlayer(player, target, "speed");
                    player.sendMessage(ServerManager.getInstance().getMessageModule().getReportedSucces().replace("%target%", target.getName()).replace("%reden%", "speed"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("servermanager.report.see")) {
                            players.spigot().sendMessage(
                                    new ComponentBuilder("Er is een report binnen gekomen!\n\nKlik ")
                                            .color(ChatColor.GOLD)
                                            .append("HIER")
                                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/showreports " + target.getName()))
                                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klik hier om de report te zien").color(ChatColor.AQUA).create()))
                                            .color(ChatColor.BLUE)
                                            .bold(false)
                                            .underlined(true)
                                            .append(" om " + target.getName() + " zijn reports te zien.")
                                            .reset()
                                            .color(ChatColor.GOLD)
                                            .create()
                            );
                        }
                    }
                }
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
            for (Player players : Bukkit.getOnlinePlayers()) {
                returnList.add(players.getName());
            }
        }

        if (args.length == 2) {
            for (ReportType reportType : ReportType.values()) {
                returnList.add(reportType.getReportTypeName());
            }
        }

        return returnList;
    }
}
