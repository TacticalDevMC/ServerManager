package me.flexdevelopment.servermanager.inventory.menus;

import api.vortexgames.inventory.ItemBuilder;
import com.google.common.base.Joiner;
import lombok.Getter;
import me.flexdevelopment.servermanager.api.utils.Chat;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class PluginsListMenu {

    @Getter
    private static String pluginsMenuInventoryName = color("&3Bekijk alle plugins");

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 36, pluginsMenuInventoryName);

        for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {

            String authors = plugin.getDescription().getAuthors().size() == 0 ? ChatColor.RED + "Geen Author(s)" : Joiner.on(", ").join(plugin.getDescription().getAuthors());
            String authorsMore = plugin.getDescription().getAuthors().size() > 3 ? ChatColor.RED + "Meer dan 3 authors" : Joiner.on(", ").join(plugin.getDescription().getAuthors());

            if (plugin.getDescription().getAuthors().size() > 3) {
                ItemStack plugins = CreateItemStacks.createItem(new ItemBuilder(Material.BEDROCK, 1), "&6" + plugin.getName(), Chat.color("&7Version: &6" + plugin.getDescription().getVersion()), Chat.color("&7Author(s): &6" + authorsMore));
                inventory.addItem(plugins);
            } else {
                ItemStack plugins = CreateItemStacks.createItem(new ItemBuilder(Material.BEDROCK, 1), "&6" + plugin.getName(), Chat.color("&7Version: &6" + plugin.getDescription().getVersion()), Chat.color("&7Author(s): &6" + authors));
                inventory.addItem(plugins);
            }
        }

        player.openInventory(inventory);
    }
}
