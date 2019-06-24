package me.flexdevelopment.servermanager.modules.player.commands.base;

import me.flexdevelopment.servermanager.ServerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public abstract class CommandBase implements CommandExecutor, TabCompleter {
    private String command;
    private String permission;

    public CommandBase(String command, String permission) {
        this.command = command;
        this.permission = permission;
    }

    public CommandBase(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (this.getPermission() != null) {
            String NO_PERMISSION = ServerManager.getInstance().getMessageModule().getGeenPerms();
            if (this.getPermission().equalsIgnoreCase("op") && !sender.isOp()) {
                sender.sendMessage(NO_PERMISSION);
                return true;
            }

            if (!sender.hasPermission(this.getPermission())) {
                sender.sendMessage(NO_PERMISSION);
                return true;
            }
        }

        this.executeCommand(sender, cmd, label, args);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (this.getPermission() != null) {
            if (this.getPermission().equalsIgnoreCase("op")) {
                if (!sender.isOp()) {
                    return Collections.emptyList();
                }
            } else if (!sender.hasPermission(this.getPermission())) {
                return Collections.emptyList();
            }
        }

        return this.tabComplete(sender, cmd, label, args);
    }

    public abstract boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args);
}