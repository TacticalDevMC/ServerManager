package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandSpyCommand extends CommandBase {


    public CommandSpyCommand() {
        super("commandspy", Commands.COMMANDSPYCOMMAND.getPermission());
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        String commandspy = ServerManager.getInstance().getConfigModule().getCommandSpy();

        if (commandspy.equals("Aan")) {
            if (!ServerManager.getInstance().getCommandSpy().contains(player.getUniqueId())) {
                ServerManager.getInstance().getCommandSpy().add(player.getUniqueId());
                player.sendMessage(ServerManager.getInstance().getMessageModule().getCommandSpyAan());
            } else if (ServerManager.getInstance().getCommandSpy().contains(player.getUniqueId())) {
                ServerManager.getInstance().getCommandSpy().remove(player.getUniqueId());
                player.sendMessage(ServerManager.getInstance().getMessageModule().getCommandSpyUit());
            }
        } else if (commandspy.equals("Uit")) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getCommandSpyDisabled());
        }

        return false;

    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}
