package me.flexdevelopment.servermanager.modules.player.listeners.player;

import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String loginMessage = ServerManager.getInstance().getConfigModule().getLoginMessage();

        if (loginMessage.equalsIgnoreCase("Geen")) {
            event.setJoinMessage(color("&e" + player.getName() + " joined the game"));
        } else {
            event.setJoinMessage(loginMessage.replace("%player%", player.getName()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        String logoutMessage = ServerManager.getInstance().getConfigModule().getLogoutMessage();

        if (logoutMessage.equalsIgnoreCase("Geen")) {
            event.setQuitMessage(color("&e" + player.getName() + " left the game"));
        } else {
            event.setQuitMessage(logoutMessage.replace("%player%", player.getName()));
        }
    }
}
