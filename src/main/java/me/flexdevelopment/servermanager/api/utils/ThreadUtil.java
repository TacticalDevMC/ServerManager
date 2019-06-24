package me.flexdevelopment.servermanager.api.utils;

import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.Bukkit;

public class ThreadUtil {

    /**
     * Run a task in a separate thread.
     *
     * @param runnable the task.
     */
    public static void async(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(ServerManager.getInstance(), runnable);
    }

    /**
     * Run a task in the main thread.
     *
     * @param runnable the task.
     */
    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask(ServerManager.getInstance(), runnable);
    }

}