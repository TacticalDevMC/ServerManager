package me.flexdevelopment.servermanager.modules.player.commands.subcommands.main;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.updater.Updat3r;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class UpdateInfoSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        player.sendMessage(color("&3Cached 'latest': &b" + Updat3r.getInstance().getLatestCached().getVersion()));
        player.sendMessage(color("&3Latest: &b" + Updat3r.getInstance().getLatestUpdate(Updat3r.PROJECT_NAME, Updat3r.API_KEY).getVersion()));
        player.sendMessage(color("&3Actual version: &b" + ((ServerManager) ServerManager.getPlugin(ServerManager.class)).getDescription().getVersion()));

        return false;
    }
}
