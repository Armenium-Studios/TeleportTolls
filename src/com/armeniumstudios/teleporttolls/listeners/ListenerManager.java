package com.armeniumstudios.teleporttolls.listeners;

import org.bukkit.event.Listener;

import com.armeniumstudios.teleporttolls.TeleportTolls;

public class ListenerManager {
    private static Listener[] listeners = new Listener[] {
            // Add listeners here
    };

    public static void init() {
        TeleportTolls plugin = TeleportTolls.getInstance();

        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
