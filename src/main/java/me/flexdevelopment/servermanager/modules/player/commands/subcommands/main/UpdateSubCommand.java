package me.flexdevelopment.servermanager.modules.player.commands.subcommands.main;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.updater.Updat3r;
import me.flexdevelopment.servermanager.api.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;


public class UpdateSubCommand {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (!Updat3r.getInstance().getLatestCached().isNewer()) {
            sender.sendMessage(color("&cEr is geen update beschikbaar!"));
            return true;
        }

        sender.sendMessage(color("&3We gaan de update nu installeren!"));
        Updat3r.getInstance().downloadLatest(Updat3r.getInstance().getLatestCached().getDownloadLink(), "ServerManage", ServerManager.getPlugin(ServerManager.class));
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.kickPlayer(color(Chat.getPrefix() + "&4&lLET OP: &cWe gaan een update uitvoeren."));
        }
        Bukkit.reload();

        return false;
    }
}
