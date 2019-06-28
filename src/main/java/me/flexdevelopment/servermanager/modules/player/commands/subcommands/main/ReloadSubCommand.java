package me.flexdevelopment.servermanager.modules.player.commands.subcommands.main;

import me.flexdevelopment.servermanager.inventory.menus.ReloadMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        ReloadMenu reloadMenu = new ReloadMenu();

        reloadMenu.open(player);

        return false;
    }
}
