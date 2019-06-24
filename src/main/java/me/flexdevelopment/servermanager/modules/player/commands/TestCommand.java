package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.inventory.menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TestCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this.");
            return false;
        }


        Player player = (Player) sender;

        MainMenu mainMenu = new MainMenu();

        mainMenu.open(player);

        return false;
    }

//    @EventHandler
//    public void test(InventoryClickEvent event) {
//        event.setCancelled(true);
//    }
}
