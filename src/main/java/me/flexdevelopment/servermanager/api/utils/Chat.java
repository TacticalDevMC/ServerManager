package me.flexdevelopment.servermanager.api.utils;

import lombok.Getter;
import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Chat {

    @Getter
    private static String prefix = color(ServerManager.getInstance().getMessageModule().getPrefix());

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void debug(String message) {
        Bukkit.getConsoleSender().sendMessage("[ServerManager-Debug] " + message);
    }
}
