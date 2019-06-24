package me.flexdevelopment.servermanager.modules.player.listeners.inventory;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.inventory.menus.*;
import me.joran.superwhitelist.SuperWhitelist;
import me.joran.superwhitelist.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class ClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if (event.getCurrentItem() == null || event.getClickedInventory().getType() == null || event.getCurrentItem().getType() == Material.AIR)
            return;

        if (inventory.getTitle() == null) return;
        if (inventory == null) return;

        if (inventory.getTitle().equalsIgnoreCase(MainMenu.getMainMenuInventoryName())) {
            event.setCancelled(true);
            switch (event.getSlot()) {
                case 11:
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getWhitelistName())) {
                        WhitelistMenu whitelistMenu = new WhitelistMenu();

                        whitelistMenu.open(player);
                    }
                    break;
                case 13:
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getPlayersName())) {
//                        PlayersListMenu playersListMenu = new PlayersListMenu();
//
//                        playersListMenu.open(player);
                        player.sendMessage(color("&cDit is momenteel nog niet toegankelijk, dit wordt toegankelijk in één van de volgende updates."));
                    }
                    break;
                case 15:
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getOPPlayersName())) {
                        OPPlayersListMenu opPlayersListMenu = new OPPlayersListMenu();

                        opPlayersListMenu.open(player);
                    }
                    break;
                case 21:
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getLoginMessageName())) {
                        ServerManager.getInstance().getLoginMessagePlayer().add(player.getUniqueId());
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getLoginMessageChatMessage());
                        player.closeInventory();
                    }
                case 23:
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getLogoutMessageName())) {
                        ServerManager.getInstance().getlogoutMessagePlayer().add(player.getUniqueId());
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getLogoutMessageChatMessage());
                        player.closeInventory();
                    }
                    break;
                default:
            }
        } else if (inventory.getTitle().equalsIgnoreCase(WhitelistMenu.getWhitelistInventoryName())) {
            event.setCancelled(true);
            switch (event.getSlot()) {
                case 3:
                    if (Bukkit.getPluginManager().getPlugin("SuperWhitelist") != null) {
                        FileManager.get("whitelist/config.yml").set("status", "Aan");
                        FileManager.save(SuperWhitelist.getInstance(), "whitelist/config.yml");
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getWhitelistAan());
                    } else if (Bukkit.getPluginManager().getPlugin("SuperWhitelist") == null) {
                        Bukkit.getServer().setWhitelist(true);
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getWhitelistAan());
                    }
                    break;
                case 5:
                    if (Bukkit.getPluginManager().getPlugin("SuperWhitelist") != null) {
                        FileManager.get("whitelist/config.yml").set("status", "Uit");
                        FileManager.save(SuperWhitelist.getInstance(), "whitelist/config.yml");
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getWhitelistUit());
                    } else if (Bukkit.getPluginManager().getPlugin("SuperWhitelist") == null) {
                        Bukkit.getServer().setWhitelist(false);
                        player.sendMessage(ServerManager.getInstance().getMessageModule().getWhitelistUit());
                    }
                    break;
                default:
            }
        } else if (inventory.getTitle().equalsIgnoreCase(PluginsListMenu.getPluginsMenuInventoryName())) {
            event.setCancelled(true);
        } else if (inventory.getTitle().equalsIgnoreCase(OPPlayersListMenu.getPlayersListInventoryName())) {
            event.setCancelled(true);
        }
    }
}
