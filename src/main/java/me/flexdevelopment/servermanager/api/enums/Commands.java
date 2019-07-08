package me.flexdevelopment.servermanager.api.enums;

public enum Commands {

    MAINCOMMAND("servermanager", "/servermanger <help, update, updateinfo, openmenu, reload>", "De ServerManager main command", "servermanager.main", "[sm, svm, serverm, servm]"),
    PLMANAGERCOMMAND("pluginmanager", "/pluginmanager <enable, disable, load, unload, reload, dump, info, list> <plugin (Restart/reload: all, *)>", "PluginManager command", "servermanager.pluginmanager", "[plugmanager, plmanager, pluginm]"),
    REPORTCOMMAND("report", "/report <player> <type> <Hackes: speed, killaura> ", "Report een speler", null, null),
    SHOWREPORTSCOMMAND("showreports", "/showreports <player>", "Laat de speler zijn reports zien", "servermanager.report.showreports", null),
    CLEARREPORTSCOMMAND("clearreports", "/clearreports <player>", "Clear de speler zijn reports", "servermanager.report.clearreports", null),
    LATESTLOGCOMMAND("latestlog", "/latestlog <messages, commands, functions>", "Laat logs zien van alles.", "servermanager.latestlog", null),
    COMMANDSPYCOMMAND("commandspy", "/commandspy", "Zet je commandspy aan of uit", "servermanager.commandspy", null),
    CLEARCHATCOMMAND("clearchat", "/clearchat", "Clear de chat", "servermanager.clearchat", "cc", "clearc");


    private String command, usage, description, permission;
    private String[] aliases;

    Commands(String command, String usage, String description, String permission, String... aliases) {
        this.command = command;
        this.usage = usage;
        this.description = description;
        this.permission = permission;
        this.aliases = aliases;
    }

    public String getCommand() {
        return command;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public String[] getAliases() {
        return aliases;
    }
}
