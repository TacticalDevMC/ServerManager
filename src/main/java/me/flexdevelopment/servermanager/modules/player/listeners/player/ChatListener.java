package me.flexdevelopment.servermanager.modules.player.listeners.player;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();

        if (ServerManager.getInstance().getLoginMessagePlayer().contains(player.getUniqueId())) {
            if (message.contains("Cancel") || message.contains("cancel")) {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLoginMessageCanceled());
                event.setCancelled(true);
                ServerManager.getInstance().getLoginMessagePlayer().remove(player.getUniqueId());
            } else if (message.contains("Geen") || message.contains("geen")) {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLoginMessageSetToGeen());
                ServerManager.getInstance().getConfigModule().setLoginMessage("Geen");
                event.setCancelled(true);
                ServerManager.getInstance().getLoginMessagePlayer().remove(player.getUniqueId());
            } else {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLoginMessageSuccesSet());
                ServerManager.getInstance().getConfigModule().setLoginMessage(message);
                event.setCancelled(true);
                ServerManager.getInstance().getLoginMessagePlayer().remove(player.getUniqueId());
            }
        }

        if (ServerManager.getInstance().getlogoutMessagePlayer().contains(player.getUniqueId())) {
            if (message.contains("Cancel") || message.contains("cancel")) {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLogoutMessageCanceled());
                event.setCancelled(true);
                ServerManager.getInstance().getlogoutMessagePlayer().remove(player.getUniqueId());
            } else if (message.contains("Geen") || message.contains("geen")) {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLogoutMessageSetToGeen());
                ServerManager.getInstance().getConfigModule().setLoginMessage("Geen");
                event.setCancelled(true);
                ServerManager.getInstance().getlogoutMessagePlayer().remove(player.getUniqueId());
            } else {
                player.sendMessage(ServerManager.getInstance().getMessageModule().getLogoutMessageSuccesSet());
                ServerManager.getInstance().getConfigModule().setLogoutMessage(message);
                event.setCancelled(true);
                ServerManager.getInstance().getlogoutMessagePlayer().remove(player.getUniqueId());
            }
        }
    }
}
