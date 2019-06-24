package me.flexdevelopment.servermanager.inventory.menus;

import api.vortexgames.inventory.ItemBuilder;
import lombok.Getter;
import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.Chat;
import me.flexdevelopment.servermanager.api.utils.Icons;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class WhitelistMenu {

    @Getter
    private static String aanName = ServerManager.getInstance().getConfigModule().getWhitelistItemAanName().replace("%diamond%", Icons.DIAMOND);
    @Getter
    private static String uitName = ServerManager.getInstance().getConfigModule().getWhitelistItemUitName().replace("%diamond%", Icons.DIAMOND);

    @Getter
    private static String whitelistInventoryName = color("&3Manager &7" + Icons.ARROW + " &3&lWhitelist");

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9, whitelistInventoryName);

        ItemStack aan = CreateItemStacks.createItem(new ItemBuilder(Material.WOOL, 1, (short) 5), aanName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de Whitelist &aaan &7te zetten"), " ");
        ItemStack uit = CreateItemStacks.createItem(new ItemBuilder(Material.WOOL, 1, (short) 14), uitName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de Whitelist &4uit &7te zetten"), " ");
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, aan);
        inventory.setItem(4, glass);
        inventory.setItem(5, uit);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);

        player.openInventory(inventory);
    }
}
