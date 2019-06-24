package me.flexdevelopment.servermanager.modules.player.commands;

import me.flexdevelopment.servermanager.ServerManager;
import me.flexdevelopment.servermanager.api.enums.Commands;
import me.flexdevelopment.servermanager.modules.player.commands.base.CommandBase;
import me.flexdevelopment.servermanager.modules.player.commands.subcommands.main.HelpSubCommand;
import me.flexdevelopment.servermanager.modules.player.commands.subcommands.main.OpenMenuSubCommand;
import me.flexdevelopment.servermanager.modules.player.commands.subcommands.main.UpdateInfoSubCommand;
import me.flexdevelopment.servermanager.modules.player.commands.subcommands.main.UpdateSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerManagerCommand extends CommandBase {

    public ServerManagerCommand() {
        super("servermanager", Commands.MAINCOMMAND.getPermission());
    }

    // subcommands
    HelpSubCommand help = new HelpSubCommand();
    UpdateInfoSubCommand updateInfo = new UpdateInfoSubCommand();
    OpenMenuSubCommand openMenu = new OpenMenuSubCommand();
    UpdateSubCommand update = new UpdateSubCommand();

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ServerManager.getInstance().getMessageModule().getMainArgsNotFound());
            return false;
        }

        switch (args[0]) {

            case "help":
                this.help.onCommand(sender, cmd, label, args);
                break;
            case "updateinfo":
                this.updateInfo.onCommand(sender, cmd, label, args);
                break;
            case "openmenu":
                this.openMenu.onCommand(sender, cmd, label, args);
                break;
            case "update":
                this.update.onCommand(sender, cmd, label, args);
                break;
            default:
                player.sendMessage(ServerManager.getInstance().getMessageModule().getGeenSubCommandFound());
        }

        return false;
    }

    public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> returnList = new ArrayList();
        if (args.length == 1) {
            returnList.add("help");
            returnList.add("updateinfo");
            returnList.add("openmenu");
            returnList.add("update");
        }
        return returnList;
    }
}
