package me.flexdevelopment.servermanager.inventory.menus;

import api.vortexgames.inventory.ItemBuilder;
import lombok.Getter;
import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.Chat;
import me.flexdevelopment.servermanager.api.utils.Icons;
import me.flexdevelopment.servermanager.inventory.creatings.CreateItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

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
    private static String loginMessageName = ServerManager.getInstance().getConfigModule().getLoginMessageItemName();
    @Getter
    private static String logoutMessageName = ServerManager.getInstance().getConfigModule().getLogoutMessageItemName();
    @Getter
    private static String nextPageName = "&6Volgende pagina ➜";
//    private static String nextPageName = "&6Volgende pagina";

    @Getter
    private static String mainMenuInventoryName = color("&3Bekijk alle opties. Page &3&l(1/2)");

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 36, mainMenuInventoryName);

        ItemStack whitelist = CreateItemStacks.createItem(new ItemBuilder(Material.FEATHER), whitelistName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de &6Whitelist &7aan te passen"), " ");
        inventory.setItem(11, whitelist);

        if (playersHead.startsWith("http://")) {
            ItemStack players = CreateItemStacks.createSkullWithUrl(playersHead, playersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6Players &7te zien."), " ");
            inventory.setItem(13, players);
        } else {
            ItemStack players = CreateItemStacks.createSkullWithOwner(playersHead, playersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6Players &7te zien."), " ");
            inventory.setItem(13, players);
        }

        ItemStack OPPlayers = CreateItemStacks.createSkullWithUrl("http://textures.minecraft.net/texture/4a15fb3ea62d130df80796c62e478f07bd1565cb551fe246bc690fd1d555ac"
                , OPPlayersName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om alle &6OP-Players &7te zien."), " ");
        inventory.setItem(15, OPPlayers);

        ItemStack loginMessage = CreateItemStacks.createItem(new ItemBuilder(Material.PAPER, 1), loginMessageName, " ", color("&7Klik om de &6LoginMessage &7te veranderen."), " ");
        inventory.setItem(21, loginMessage);

        ItemStack logoutMessage = CreateItemStacks.createItem(new ItemBuilder(Material.PAPER, 1), logoutMessageName, " ", color("&7Klik om de &6LogoutMessage &7te veranderen."), " ");
        inventory.setItem(23, logoutMessage);

        ItemStack nextPage = CreateItemStacks.createSkullWithUrl("http://textures.minecraft.net/texture/19bf3292e126a105b54eba713aa1b152d541a1d8938829c56364d178ed22bf", nextPageName, "", color("&7Klik om naar de volgende pagina te gaan."), "");
        inventory.setItem(31, nextPage);

        player.openInventory(inventory);
    }


    @Getter
    private static String commandSpyName = ServerManager.getInstance().getConfigModule().getCommandSpyItemName();
    private static String commandSpyItemEnchanted = ServerManager.getInstance().getConfigModule().getCommandSpyItemEnchanted();

    @Getter
    private static String pageBackName = "&6" + Icons.ARROW_LEFT_BOLD + " Vorige pagina";

    @Getter
    private static String mainMenuInventoryName2 = color("&3Bekijk alle opties. Page &3&l(2/2)");

    public void openPage2(Player player) {

        Inventory inventory2 = Bukkit.createInventory(null, 36, mainMenuInventoryName2);

        if (ServerManager.getInstance().getConfigModule().getCommandSpy().equals("Uit")) {
            ItemStack commandSpy = CreateItemStacks.createItem(new ItemBuilder(Material.COMMAND), commandSpyName, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de &6CommandSpy &7aan te zetten"), " ");
            inventory2.setItem(11, commandSpy);
        } else if (ServerManager.getInstance().getConfigModule().getCommandSpy().equals("Aan")) {
            if (commandSpyItemEnchanted.equals("True")) {
                ItemStack commandSpy = CreateItemStacks.createItemWithEnchantment(new ItemBuilder(Material.COMMAND), commandSpyName, Enchantment.FIRE_ASPECT, 1, " ", Chat.color("&c" + Icons.ARROW + " &7Klik hier om de &6CommandSpy &7uit te zetten"), " ");
                inventory2.setItem(11, commandSpy);
            }
        }

        ItemStack pageBack = CreateItemStacks.createSkullWithUrl("http://textures.minecraft.net/texture/bd69e06e5dadfd84e5f3d1c21063f2553b2fa945ee1d4d7152fdc5425bc12a9", pageBackName, "", color("&7Klik om terug te gaan naar de vorige pagina."), "");
        inventory2.setItem(31, pageBack);

        player.openInventory(inventory2);
    }
}
