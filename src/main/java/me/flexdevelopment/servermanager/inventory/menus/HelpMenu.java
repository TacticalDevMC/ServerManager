package me.flexdevelopment.servermanager.inventory.menus;

import api.vortexgames.inventory.ItemBuilder;
import lombok.Getter;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class HelpMenu {

    @Getter
    private static String helpInventoryName = color("&3Help Menu");

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 36, helpInventoryName);

        for (Commands mainCommands : Commands.values()) {
            ItemStack itemStack = CreateItemStacks.createItem(new ItemBuilder(Material.QUARTZ_BLOCK, 1), color("&6" + mainCommands.getCommand()), " ", color("&7Help menu van het commando &6" + mainCommands.getCommand() + "&7."), " ");

            inventory.addItem(itemStack);
        }

        player.openInventory(inventory);
    }
}
