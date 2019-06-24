package me.flexdevelopment.servermanager.api;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;

import static me.flexdevelopment.servermanager.api.utils.Chat.color;

public class ConfigModule {

    private ServerManager plugin;

    public ConfigModule(ServerManager serverManager) {
        plugin = serverManager;
    }

    // whitelist
    private String whitelistItemName = FileManager.get("config.yml").getString("Items.Whitelist.Name");
    private String whitelistItemAanName = FileManager.get("config.yml").getString("Items.Whitelist.Types.Aan.Name");
    private String whitelistItemUitName = FileManager.get("config.yml").getString("Items.Whitelist.Types.Uit.Name");

    // players
    private String playersName = FileManager.get("config.yml").getString("Items.Players.Name");
    private String playersHead = FileManager.get("config.yml").getString("Items.Players.SkullOwner");

    // OPPlayers
    private String OPPlayersName = FileManager.get("config.yml").getString("Items.OPPlayers.Name");

    // messages
    private String loginMessage = FileManager.get("config.yml").getString("login-message");
    private String loginMessageItemName = FileManager.get("config.yml").getString("Items.LoginMessage.Name");

    private String logoutMessage = FileManager.get("config.yml").getString("logout-message");
    private String logoutMessageItemName = FileManager.get("config.yml").getString("Items.LogoutMessage.Name");

    public String getWhitelistItemName() {
        return color(whitelistItemName);
    }

    public String getWhitelistItemAanName() {
        return color(whitelistItemAanName);
    }

    public String getWhitelistItemUitName() {
        return color(whitelistItemUitName);
    }

    public String getPlayersName() {
        return color(playersName);
    }

    public String getPlayersHead() {
        return color(playersHead);
    }

    public String getOPPlayersName() {
        return color(OPPlayersName);
    }

    public String getLoginMessage() {
        return color(loginMessage);
    }

    public void setLoginMessage(String loginMessage) {
        FileManager.get("config.yml").set("login-message", loginMessage);
        FileManager.save(plugin, "config.yml");
    }

    public String getLoginMessageItemName() {
        return color(loginMessageItemName);
    }

    public String getLogoutMessage() {
        return color(logoutMessage);
    }

    public void setLogoutMessage(String logoutMessage) {
        FileManager.get("config.yml").set("logout-message", logoutMessage);
        FileManager.save(plugin, "config.yml");
    }

    public String getLogoutMessageItemName() {
        return color(logoutMessageItemName);
    }
}

