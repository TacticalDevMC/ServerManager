package me.flexdevelopment.servermanager.api.utils;

import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.*;

import java.io.File;

public class PluginsManager {

    private static PluginManager pluginManager = Bukkit.getPluginManager();

    public static boolean isPluginEnabled(Plugin plugin) {
        if (pluginManager.isPluginEnabled(plugin)) {
            return true;
        }
        return false;
    }

    public static void enablePlugin(Plugin plugin) {
        if (!isPluginEnabled(plugin)) {
            pluginManager.enablePlugin(plugin);
        }
    }

    public static void disablePlugin(Plugin plugin) {
        if (isPluginEnabled(plugin)) {
            pluginManager.disablePlugin(plugin);
        }
    }

    public static boolean pluginExists(Plugin plugin) {
        for (Plugin plugins : Bukkit.getPluginManager().getPlugins()) {
            Chat.debug("Debug 3: " + plugins.getName() + " " + plugin.getName());
            if (plugins.getName().equals(plugin.getName())) {
                Chat.debug("Debug 4: " + plugin.getName());
                return true;
            }
        }
        return false;
    }

    public static Plugin getPluginByName(String[] args, int start) {
        return getPluginByName(StringUtil.consolidateStrings(args, start));
    }

    public static Plugin getPluginByName(String name) {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (name.equalsIgnoreCase(plugin.getName())) {
                return plugin;
            }
        }
        return null;
    }

    public static String getPluginVersion(String name) {
        Plugin plugin = getPluginByName(name);
        if (plugin != null && plugin.getDescription() != null) {
            return plugin.getDescription().getVersion();
        }
        return null;
    }

    public static boolean isIgnored(Plugin plugin) {
        return isIgnored(plugin.getName());
    }

    /**
     * Checks whether the plugin is ignored.
     *
     * @param plugin the plugin to check
     * @return whether the plugin is ignored
     */
    public static boolean isIgnored(String plugin) {
        for (String name : ServerManager.getInstance().getIgnoredPlugins()) {
            if (name.equalsIgnoreCase(plugin)) {
                return true;
            }
        }
        return false;
    }

    public static String load(String name) {

        Plugin target = null;

        File pluginDir = new File("plugins");

        if (!pluginDir.isDirectory()) {
//            return ServerManager.getInstance().getMessageFormatter().format("load.plugin-directory");
            return "directory not found.";
        }

        File pluginFile = new File(pluginDir, name + ".jar");

        if (!pluginFile.isFile()) {
            for (File f : pluginDir.listFiles()) {
                if (f.getName().endsWith(".jar")) {
                    try {
                        PluginDescriptionFile desc = ServerManager.getInstance().getPluginLoader().getPluginDescription(f);
                        if (desc.getName().equalsIgnoreCase(name)) {
                            pluginFile = f;
                            break;
                        }
                    } catch (InvalidDescriptionException e) {
                        return "Cannot find load.";
                    }
                }
            }
        }

        try {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        } catch (InvalidDescriptionException e) {
            e.printStackTrace();
            return "Invalid description.";
        } catch (InvalidPluginException e) {
            e.printStackTrace();
            return "Invalid plugin.";
        }

        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);

        return "Plugin loaded:" + target.getName();
    }
}
