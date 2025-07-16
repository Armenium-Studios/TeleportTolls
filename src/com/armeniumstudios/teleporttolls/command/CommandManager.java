package com.armeniumstudios.teleporttolls.command;

import org.bukkit.command.PluginCommand;

import com.armeniumstudios.teleporttolls.TeleportTolls;

public class CommandManager {
    public static void init() {
        PluginCommand languageCommand = TeleportTolls.getInstance().getCommand("language");
        languageCommand.setExecutor(new LanguageCommand());
        languageCommand.setTabCompleter(new LanguageCompleter());
    }
}
