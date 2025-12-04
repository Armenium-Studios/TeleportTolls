package com.armeniumstudios.teleporttolls;

import java.util.logging.Level;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.armeniumstudios.teleporttolls.command.CommandManager;
import com.armeniumstudios.teleporttolls.listeners.ListenerManager;
import com.armeniumstudios.teleporttolls.manager.ConfigManager;
import com.armeniumstudios.teleporttolls.manager.LocaleManager;
import com.armeniumstudios.teleporttolls.manager.PlayerConfigManager;
import com.armeniumstudios.teleporttolls.util.PluginUtilities;

import net.milkbowl.vault.economy.Economy;

public class TeleportTolls extends JavaPlugin {

    private static TeleportTolls INSTANCE;
    private static Economy econ = null;

    public static TeleportTolls getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        TeleportTolls.INSTANCE = this;

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.saveDefaultConfig();
        ConfigManager.refresh();
        PlayerConfigManager.init();
        PluginUtilities.init();
        ListenerManager.init();
        LocaleManager.init();
        CommandManager.init();

        this.getLogger().log(Level.INFO, "Plugin initialized!");
    }
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onDisable() {
        //On disable
    }
}

