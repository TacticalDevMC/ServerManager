package me.flexdevelopment.servermanager.api.managers;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private ServerManager plugin;

    public ReportManager(ServerManager serverManager) {
        plugin = serverManager;
    }

    public void reportPlayer(Player moderator, Player target, String reden) {

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String reason = (String) FileManager.get("reports.yml").get("Players." + target.getUniqueId() + ".ReportType");

        if (reden.equals(reason)) {
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".Name", target.getName());
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".Moderator", moderator.getName());
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".ReportType", reden);
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".ReportDatum", format.format(now));
            FileManager.save(plugin, "reports.yml");
        } else {
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".Name", target.getName());
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".Moderator", moderator.getName());
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".ReportType", reden);
            FileManager.get("reports.yml").set("Players." + target.getUniqueId() + ".ReportDatum", format.format(now));
            FileManager.save(plugin, "reports.yml");
        }
    }

    public void showReportsFromPlayer(Player moderator, Player target) {

        if (FileManager.get("reports.yml").get("Players." + target.getUniqueId()) == null) {
            moderator.sendMessage(ServerManager.getInstance().getMessageModule().getGeenReportsVoorDezeSpeler().replace("%target%", target.getName()));
            return;
        }

        String name = (String) FileManager.get("reports.yml").get("Players." + target.getUniqueId() + ".Name");
        String reden = (String) FileManager.get("reports.yml").get("Players." + target.getUniqueId() + ".ReportType");
        String datum = (String) FileManager.get("reports.yml").get("Players." + target.getUniqueId() + ".ReportDatum");
        String mod = (String) FileManager.get("reports.yml").get("Players." + target.getUniqueId() + ".Moderator");

        moderator.sendMessage(ServerManager.getInstance().getMessageModule().getInfoReportHeader().replace("%reportedPlayer%", name));
        moderator.sendMessage(ServerManager.getInstance().getMessageModule().getInfoReden().replace("%reportReden%", reden));
        moderator.sendMessage(ServerManager.getInstance().getMessageModule().getInfoDatum().replace("%reportDatum%", datum));
        moderator.sendMessage(ServerManager.getInstance().getMessageModule().getInfoModerator().replace("%reportModerator%", mod));

        moderator.spigot().sendMessage(
                new ComponentBuilder("Klik ")
                        .color(ChatColor.GOLD)
                        .append("HIER")
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clearreports " + target.getName()))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klik hier om deze report te deleten").color(ChatColor.AQUA).create()))
                        .color(ChatColor.BLUE)
                        .bold(false)
                        .underlined(true)
                        .append(" om deze report te verwijderen.")
                        .reset()
                        .color(ChatColor.GOLD)
                        .create()
        );
    }

    public void clearReportsFromPlayer(Player moderator, Player target) {
        if (FileManager.get("reports.yml").get("Players." + target.getUniqueId()) == null) {
            moderator.sendMessage(ServerManager.getInstance().getMessageModule().getGeenReportsVoorDezeSpeler().replace("%target%", target.getName()));
            return;
        }

        FileManager.get("reports.yml").set("Players." + target.getUniqueId(), null);
        FileManager.save(plugin, "reports.yml");
        moderator.sendMessage(ServerManager.getInstance().getMessageModule().getReportVerwijderd().replace("%target%", target.getName()));
    }
}
