package me.flexdevelopment.servermanager.api.message;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.Chat;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import org.bukkit.entity.Player;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class MessageManager {

    private ServerManager plugin;

    public MessageManager(ServerManager serverManager) {
        this.plugin = serverManager;
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    public String getMessageWithPath(String messagePath) {
        String message = FileManager.get("messages.yml").getString(messagePath);

        return color(message).replace("%prefix%", Chat.getPrefix());
    }

    public String replaceConfigMessage(String messagePath, String replace, String toReplace) {
        return getMessageWithPath(messagePath).replace(replace, toReplace);
    }
}
