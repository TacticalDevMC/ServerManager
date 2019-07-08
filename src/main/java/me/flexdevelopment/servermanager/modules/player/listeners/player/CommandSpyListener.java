package me.flexdevelopment.servermanager.modules.player.listeners.player;

import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class CommandSpyListener implements Listener {

    @EventHandler
    public void onCommandProccess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer(); //Player who typed
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (ServerManager.getInstance().getCommandSpy().contains(all.getUniqueId())) {
                if (event.getMessage().contains("/")) {
                    all.sendMessage(color("&c" + player.getName() + ":&7 " + event.getMessage()));
                }
            } else {
                return;
            }
        }

        ServerManager.getInstance().getLatestLogManager().setCommandsLog(player, event.getMessage());
    }
}
