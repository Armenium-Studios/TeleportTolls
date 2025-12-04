package com.armeniumstudios.teleporttolls.command;

import org.bukkit.command.PluginCommand;

import com.armeniumstudios.teleporttolls.TeleportTolls;

public class CommandManager {
    public static void init() {
        PluginCommand teleportTollsCommand = TeleportTolls.getInstance().getCommand("teleporttolls");
        teleportTollsCommand.setExecutor(new TeleportTollsCommand());
        teleportTollsCommand.setTabCompleter(new TeleportTollsCompleter());

        PluginCommand tpToCommand = TeleportTolls.getInstance().getCommand("tpto");
        tpToCommand.setExecutor(new TeleportToCommand());
        tpToCommand.setTabCompleter(new PlayerCompleter());

        PluginCommand tpAllowCommand = TeleportTolls.getInstance().getCommand("tpallow");
        tpAllowCommand.setExecutor(new TeleportAllowCommand());
        tpAllowCommand.setTabCompleter(new PlayerCompleter());

        PluginCommand tpDenyCommand = TeleportTolls.getInstance().getCommand("tpdeny");
        tpDenyCommand.setExecutor(new TeleportDenyCommand());
        tpDenyCommand.setTabCompleter(new PlayerCompleter());
    }
}
