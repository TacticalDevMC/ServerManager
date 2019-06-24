package me.flexdevelopment.servermanager.api.message;

import org.bukkit.entity.Player;

public class MessageManager {

    private String message;

    public MessageManager(String message) {
        this.message = message;
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }
}
