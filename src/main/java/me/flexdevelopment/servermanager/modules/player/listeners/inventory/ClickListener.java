package me.flexdevelopment.servermanager.modules.player.listeners.inventory;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.Chat;
import me.flexdevelopment.servermanager.inventory.menus.*;
import me.joran.superwhitelist.SuperWhitelist;
import me.joran.superwhitelist.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
//                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getLoginMessageName())) {
//                        ServerManager.getInstance().getLoginMessagePlayer().add(player.getUniqueId());
//                        player.sendMessage(ServerManager.getInstance().getMessageModule().getLoginMessageChatMessage());
//                        player.closeInventory();
//                    }
                    player.sendMessage(color("&cDit is momenteel nog niet toegankelijk, dit wordt toegankelijk in één van de volgende updates."));
                    break;
                case 23:
//                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MainMenu.getLogoutMessageName())) {
//                        ServerManager.getInstance().getlogoutMessagePlayer().add(player.getUniqueId());
//                        player.sendMessage(ServerManager.getInstance().getMessageModule().getLogoutMessageChatMessage());
//                        player.closeInventory();
//                    }
                    player.sendMessage(color("&cDit is momenteel nog niet toegankelijk, dit wordt toegankelijk in één van de volgende updates."));
                case 31:
                    MainMenu mainMenu = new MainMenu();

                    mainMenu.openPage2(player);
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
        } else if (inventory.getTitle().equalsIgnoreCase(ReloadMenu.getReloadInventoryName())) {
            event.setCancelled(true);
            switch (event.getSlot()) {
                case 3:
                    me.flexdevelopment.servermanager.api.utils.FileManager.save(ServerManager.getInstance(), "config.yml");
                    me.flexdevelopment.servermanager.api.utils.FileManager.save(ServerManager.getInstance(), "messages.yml");
                    me.flexdevelopment.servermanager.api.utils.FileManager.save(ServerManager.getInstance(), "reports.yml");
                    me.flexdevelopment.servermanager.api.utils.FileManager.reload(ServerManager.getInstance(), "config.yml");
                    me.flexdevelopment.servermanager.api.utils.FileManager.reload(ServerManager.getInstance(), "messages.yml");
                    me.flexdevelopment.servermanager.api.utils.FileManager.reload(ServerManager.getInstance(), "reports.yml");
                    Bukkit.getPluginManager().getPlugin("ServerManager").reloadConfig();
                    player.closeInventory();
                    player.sendMessage(ServerManager.getInstance().getMessageModule().getReloadPluginSucces());
                    break;
                case 5:
                    player.sendMessage(ServerManager.getInstance().getMessageModule().getReloadServerSucces());
                    player.closeInventory();
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.kickPlayer(color(Chat.getPrefix() + "&c&lDe server gaat reloaden, rejoin a.u.b."));
                    }
                    Bukkit.reload();
                    break;
                default:
            }
        } else if (inventory.getTitle().equalsIgnoreCase(MainMenu.getMainMenuInventoryName2())) {
            event.setCancelled(true);
            switch (event.getSlot()) {
                case 11:
                    if (ServerManager.getInstance().getConfigModule().getCommandSpy().equals("Uit")) {
                        player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.CommandSpy.commandSpySetAan"));
                        ServerManager.getInstance().getConfigModule().setCommandSpy("Aan");
                        player.closeInventory();
                    } else if (ServerManager.getInstance().getConfigModule().getCommandSpy().equals("Aan")) {
                        player.sendMessage(ServerManager.getInstance().getMessageManager().getMessageWithPath("Commands.CommandSpy.commandSpySetUit"));
                        ServerManager.getInstance().getConfigModule().setCommandSpy("Uit");
                        player.closeInventory();
                    }
                    break;
                case 31:
                    MainMenu mainMenu = new MainMenu();

                    mainMenu.open(player);
                    break;
                default:
            }
        }
    }
}
