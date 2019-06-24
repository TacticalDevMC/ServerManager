package me.flexdevelopment.servermanager.modules.player.commands.subcommands.main;

import me.flexdevelopment.servermanager.inventory.menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenMenuSubCommand {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        MainMenu mainMenu = new MainMenu();

        mainMenu.open(player);

        return false;
    }
}
