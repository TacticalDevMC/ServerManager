package me.flexdevelopment.servermanager.modules;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.utils.FileManager;

public class PlayerModule {

    public PlayerModule(ServerManager serverManager) {

        // files
        FileManager.load(serverManager, "config.yml");
        FileManager.load(serverManager, "messages.yml");
        FileManager.load(serverManager, "reports.yml");
        FileManager.load(serverManager, "logs/message.yml");
        FileManager.load(serverManager, "logs/commands.yml");
    }
}
