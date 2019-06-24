package me.flexdevelopment.servermanager.modules.player.commands.subcommands.main;

import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.inventory.menus.HelpMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class HelpSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        player.sendMessage(color("&7--------&3Help&7--------"));
        for (Commands command : Commands.values()) {
            player.sendMessage(color("&7" + command.getUsage() + " &8- &6" + command.getDescription()));
        }
        player.sendMessage(color("&7--------&3Help&7--------"));

//        HelpMenu helpMenu = new HelpMenu();
//
//        helpMenu.open(player);

        return false;
    }
}
