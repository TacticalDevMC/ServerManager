package me.flexdevelopment.servermanager;

import lombok.Getter;
import me.flexdevelopment.servermanager.api.ConfigModule;
import me.flexdevelopment.servermanager.api.message.MessageModule;
import me.flexdevelopment.servermanager.api.updater.Updat3r;
import me.flexdevelopment.servermanager.api.utils.FileManager;
import me.flexdevelopment.servermanager.modules.PlayerModule;
import me.flexdevelopment.servermanager.modules.player.commands.PLManagerCommand;
import me.flexdevelopment.servermanager.modules.player.commands.ServerManagerCommand;
import me.flexdevelopment.servermanager.modules.player.commands.TestCommand;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import me.flexdevelopment.servermanager.modules.player.listeners.inventory.ClickListener;
import me.flexdevelopment.servermanager.modules.player.listeners.player.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class ServerManager extends JavaPlugin {

    @Getter
    private static ServerManager instance;
    private ConsoleCommandSender console = Bukkit.getConsoleSender();
    @Getter
    private PlayerModule playerModule;
    @Getter
    private MessageModule messageModule;
    private List<String> ignoredPlugins = null;
    @Getter
    private ConfigModule configModule;

    private List<UUID> loginMessagePlayer;
    private List<UUID> logoutMessagePlayer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        console.sendMessage("[ServerManager] Plugin enabled! Version: " + this.getDescription().getVersion() + " Author(s): " + this.getDescription().getAuthors());

        // load modules
        playerModule = new PlayerModule(this);
        messageModule = new MessageModule(this);
        configModule = new ConfigModule(this);

        updater(this);

        registerCommands(
                new ServerManagerCommand(),
                new PLManagerCommand()
        );

        registerListeners(
                new ClickListener(),
                new ChatListener()
        );

        getCommand("test").setExecutor(new TestCommand());

        ignoredPlugins = FileManager.get("config.yml").getStringList("ignored-plugins");
        loginMessagePlayer = new ArrayList<>();
        logoutMessagePlayer = new ArrayList<>();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        console.sendMessage("[ServerManager] Plugin disabled! Version: " + this.getDescription().getVersion() + " Author(s): " + this.getDescription().getAuthors());
        ignoredPlugins = null;
    }

    private void updater(ServerManager serverManager) {
        Updat3r.getInstance().startTask();
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onJoin(PlayerJoinEvent event) {
                Updat3r.getInstance().sendUpdateMessageLater(event.getPlayer());
            }
        }, serverManager);
    }

    private void registerCommands(CommandBase... commands) {
        Arrays.stream(commands).forEach((command) -> {
            getInstance().getCommand(command.getCommand()).setExecutor(command);
            getInstance().getCommand(command.getCommand()).setTabCompleter(command);
        });
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach((listener) -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });
    }

    public List<String> getIgnoredPlugins() {
        return ignoredPlugins;
    }

    public List<UUID> getLoginMessagePlayer() {
        return loginMessagePlayer;
    }

    public List<UUID> getlogoutMessagePlayer() {
        return logoutMessagePlayer;
    }
}
