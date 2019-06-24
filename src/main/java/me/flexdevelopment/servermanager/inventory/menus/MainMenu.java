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

public class MainMenu {

    @Getter
    private static String whitelistName = ServerManager.getInstance().getConfigModule().getWhitelistItemName();
    @Getter
    private static String playersName = ServerManager.getInstance().getConfigModule().getPlayersName();
    private static String playersHead = ServerManager.getInstance().getConfigModule().getPlayersHead();
    @Getter
    private static String OPPlayersName = ServerManager.getInstance().getConfigModule().getOPPlayersName();

    @Getter
    private static String mainMenuInventoryName = color("&3Bekijk alle opties");

    public void open(Player player) {

//        if (playersHead.equalsIgnoreCase("OWN")) {
//            Inventory inventory = Bukkit.createInventory(null, 36, mainMenuInventoryName);
//
//            ItemStack whitelist = CreateItemStacks.createItem(new ItemBuilder(Material.FEATHER), whitelistName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de &6Whitelist &7aan te passen"), " ");
//            ItemStack players = CreateItemStacks.createSkullWithOwner(player.getName(), playersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6Players &7te zien."), " ");
//            ItemStack OPPlayers = CreateItemStacks.createSkullWithOwner(player.getName(), OPPlayersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6OP-Players &7te zien."), " ");
//
//            inventory.setItem(11, whitelist);
//            inventory.setItem(13, players);
//            inventory.setItem(15, OPPlayers);
//            player.openInventory(inventory);
//        } else {

        Inventory inventory = Bukkit.createInventory(null, 36, mainMenuInventoryName);

        ItemStack whitelist = CreateItemStacks.createItem(new ItemBuilder(Material.FEATHER), whitelistName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de &6Whitelist &7aan te passen"), " ");
        ItemStack players = CreateItemStacks.createSkullWithOwner(playersHead, playersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6Players &7te zien."), " ");
        ItemStack OPPlayers = CreateItemStacks.createSkullWithOwner(playersHead, OPPlayersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6OP-Players &7te zien."), " ");

        inventory.setItem(11, whitelist);
        inventory.setItem(13, players);
        inventory.setItem(15, OPPlayers);
        player.openInventory(inventory);
    }
}
