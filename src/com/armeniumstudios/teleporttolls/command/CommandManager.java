package com.armeniumstudios.teleporttolls.command;

import org.bukkit.command.PluginCommand;

import com.armeniumstudios.teleporttolls.TeleportTolls;

public class CommandManager {
    public static void init() {
        PluginCommand teleportTollsCommand = TeleportTolls.getInstance().getCommand("teleporttolls");
        teleportTollsCommand.setExecutor(new TeleportTollsCommand());
        teleportTollsCommand.setTabCompleter(new TeleportTollsCompleter());
    }
}
