package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ClearChatCommand extends CommandBase {


    public ClearChatCommand() {
        super("clearchat", Commands.CLEARCHATCOMMAND.getPermission());
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this.");
            return false;
        }

        Player player = (Player) sender;

        for (int x = 0; x < 150; x++) {
            Bukkit.broadcastMessage("");
        }


        Bukkit.broadcastMessage(ServerManager.getInstance().getMessageManager().replaceConfigMessage("Commands.ClearChat.chatClearedSuccesBroadcast", "%player%", player.getName()));

        player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.ClearChat.chatClearedSucces"));

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}
