package me.flexdevelopment.servermanager.inventory;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Manager {

    private static Player target;
    private static String inventoryTitle;
    private static HashMap<Player, String> createManager = new HashMap<>();

    public static Player getTarget() {
        return target;
    }

    public static String getInventoryTitle() {
        return inventoryTitle;
    }

    public static Manager setManager(Player target, String inventory) {
        inventory = inventory;
        target = target;
        createManager.put(target, inventory);
        return null;
    }

    public static String setInventoryTitle(String title) {
        createManager.put(getTarget(), title);
        inventoryTitle = title;
        return null;
    }

    public static Player setTarget(Player target) {
        createManager.put(target, inventoryTitle);
        return null;
    }

    public static String getTitle() {
        createManager.get(inventoryTitle);
        return null;
    }

    public static Player getTargetPlayer() {
        createManager.get(target);
        return null;
    }

}
