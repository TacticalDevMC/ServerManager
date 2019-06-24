package me.flexdevelopment.servermanager.inventory.menus;

import lombok.Getter;
import me.flexdevelopment.servermanager.api.utils.Icons;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class PlayersListMenu {

    @Getter
    private static String playersListInventoryName = color("&3Manager &7" + Icons.ARROW + " &3&lPlayers");

    public void open(Player player) {

        int count = Bukkit.getOnlinePlayers().size() - 1;
        Inventory inventory = Bukkit.createInventory(null, fit(count), playersListInventoryName);

        for (Player players : Bukkit.getOnlinePlayers()) {
            ItemStack playerStack = CreateItemStacks.createSkullWithOwner(players.getName(), color("&6" + players.getName()), "", color("&7Klik om de speler &6" + players.getName() + " &7te beheren."), "");

            inventory.addItem(playerStack);
        }

        player.openInventory(inventory);

    }

    public static int fit(int size) {
        if (size < 10 && size >= 1) {
            return 9;
        } else if (size < 19 && size > 9) {
            return 18;
        } else if (size < 28 && size > 18) {
            return 27;
        } else if (size < 37 && size > 27) {
            return 36;
        } else if (size < 46 && size > 36) {
            return 45;
        } else {
            return 54;
        }
    }

}
