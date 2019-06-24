package me.flexdevelopment.servermanager.inventory.menus;

import lombok.Getter;
import me.flexdevelopment.servermanager.api.utils.Icons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class ManagePlayerMenu {

    @Getter
    private static String managePlayerInventoryName = color("&3Manage &7" + Icons.ARROW + " ");

    public void open(Player player, Player target) {

        Inventory inventory = Bukkit.createInventory(null, 36, managePlayerInventoryName + target.getName());

        player.openInventory(inventory);
    }
}
