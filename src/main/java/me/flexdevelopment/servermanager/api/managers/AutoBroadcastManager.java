package me.flexdevelopment.servermanager.api.managers;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class AutoBroadcastManager {

    private ServerManager plugin;

    public AutoBroadcastManager(ServerManager serverManager) {
        plugin = serverManager;
    }

    int timer = FileManager.get("config.yml").getInt("Autobroadcast.Timer");

    public void sendAutoBroadcast(Player player) {

        Random random = new Random();

        List<String> broadcastMessage = FileManager.get("config.yml").getStringList("Autobroadcast.Messages");

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                player.sendMessage(color(broadcastMessage.get(random.nextInt(broadcastMessage.size()))));
            }
        }, 0, timer);
    }
}
