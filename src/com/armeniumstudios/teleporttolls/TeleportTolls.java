package com.armeniumstudios.teleporttolls;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.armeniumstudios.teleporttolls.command.CommandManager;
import com.armeniumstudios.teleporttolls.listeners.ListenerManager;
import com.armeniumstudios.teleporttolls.locale.LocaleManager;

public class TeleportTolls extends JavaPlugin {

    private static TeleportTolls INSTANCE;

    public static TeleportTolls getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        TeleportTolls.INSTANCE = this;
        this.saveDefaultConfig();
        PluginUtilities.init();
        ListenerManager.init();
        LocaleManager.init();
        CommandManager.init();

        this.getLogger().log(Level.INFO, "Plugin initialized!");
    }

    @Override
    public void onDisable() {
        //On disable
    }
}

