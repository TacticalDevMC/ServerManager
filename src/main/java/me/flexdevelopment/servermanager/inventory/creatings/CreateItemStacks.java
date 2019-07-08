package me.flexdevelopment.servermanager.inventory.creatings;

import api.vortexgames.inventory.ItemBuilder;
import api.vortexgames.inventory.SkullBuilder;
import me.flexdevelopment.servermanager.api.utils.Chat;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateItemStacks {

    public static ItemStack createSkullWithUrl(String skinUrl, String name, String... lore) {
        ItemStack skull = (new SkullBuilder(1)).setSkullSkin(skinUrl);
        ItemMeta metaSkull = skull.getItemMeta();
        metaSkull.setDisplayName(Chat.color(name));
        metaSkull.setLore(createLore(lore));
        skull.setItemMeta(metaSkull);
        return skull;
    }

    public static ItemStack createSkullWithOwner(String skullOwner, String name, String... lore) {
        ItemStack skull = (new SkullBuilder(1)).setOwner(skullOwner);
        ItemMeta metaSkull = skull.getItemMeta();
        metaSkull.setDisplayName(Chat.color(name));
        metaSkull.setLore(createLore(lore));
        skull.setItemMeta(metaSkull);
        return skull;
    }

    public static ItemStack createItem(ItemStack item, String name, String... lore) {
        ItemStack stack = new ItemBuilder(item);
        ItemMeta metaStack = stack.getItemMeta();
        metaStack.setDisplayName(Chat.color(name));
        metaStack.setLore(createLore(lore));
        stack.setItemMeta(metaStack);
        return stack;
    }

    public static ItemStack createItemWithEnchantment(ItemStack item, String name, Enchantment enchantment, int enchantLevel, String... lore) {
        ItemStack stack = new ItemBuilder(item);
        ItemMeta metaStack = stack.getItemMeta();
        metaStack.setDisplayName(Chat.color(name));
        metaStack.setLore(createLore(lore));
        metaStack.addEnchant(enchantment, enchantLevel, false);
        stack.setItemMeta(metaStack);
        return stack;
    }

    private static List<String> createLore(String... loreLines) {
        return new ArrayList(Arrays.asList(loreLines));
    }

    public static ItemStack createItem(ItemBuilder item, String name, List<String> lore) {
        ItemStack stack = new ItemBuilder(item);
        ItemMeta metaStack = stack.getItemMeta();
        metaStack.setDisplayName(Chat.color(name));
        metaStack.setLore(createLore(String.valueOf(lore)));
        stack.setItemMeta(metaStack);
        return stack;
    }
}