package me.flexdevelopment.servermanager.api.managers;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import org.bukkit.entity.Player;

import java.util.List;

public class LatestLogManager {

    private static ServerManager plugin;

    public LatestLogManager(ServerManager serverManager) {
        plugin = serverManager;
    }

    public void setMessagesLog(Player player, String message) {
        List<String> msg = FileManager.get("logs/message.yml").getStringList("Players." + player.getName());

        if (msg == null) {
            FileManager.get("logs/message.yml").set("Players.", player.getName());
        }

        msg.add(message);
        FileManager.get("logs/message.yml").set("Players." + player.getName(), msg);
        FileManager.save(plugin, "logs/message.yml");
    }

    public void setCommandsLog(Player player, String command) {
        List<String> cmd = FileManager.get("logs/commands.yml").getStringList("Players." + player.getName());

        if (cmd == null) {
            FileManager.get("logs/commands.yml").set("Players.", player.getName());
        }

        cmd.add(command);
        FileManager.get("logs/commands.yml").set("Players." + player.getName(), cmd);
        FileManager.save(plugin, "logs/commands.yml");
    }
}
