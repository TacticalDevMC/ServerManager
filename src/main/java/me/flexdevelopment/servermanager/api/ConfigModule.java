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
}
