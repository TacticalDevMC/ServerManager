package me.flexdevelopment.servermanager.inventory.menus;

import api.vortexgames.inventory.ItemBuilder;
import lombok.Getter;
import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.Icons;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class ReloadMenu {

    @Getter
    private static String reloadPluginName = ServerManager.getInstance().getConfigModule().getReloadPluginItemName().replace("%diamond%", Icons.DIAMOND);
    @Getter
    private static String reloadServerName = ServerManager.getInstance().getConfigModule().getReloadServerItemName().replace("%diamond%", Icons.DIAMOND);

    @Getter
    private static String reloadInventoryName = color("&3Manager &7" + Icons.ARROW + " &3&6Reload");

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9, reloadInventoryName);

        ItemStack reloadPlugin = CreateItemStacks.createItem(new ItemBuilder(Material.BARRIER, 1), reloadPluginName, " ", color("&7Klik om de plugin te reloaden"), " ");
        ItemStack reloadServer = CreateItemStacks.createItem(new ItemBuilder(Material.BARRIER, 1), reloadServerName, " ", color("&7Klik om de server te reloaden"), " ");

        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 7);

        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, reloadPlugin);
        inventory.setItem(4, glass);
        inventory.setItem(5, reloadServer);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);

        player.openInventory(inventory);
    }
}
