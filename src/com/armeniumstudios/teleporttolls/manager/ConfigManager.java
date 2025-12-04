package com.armeniumstudios.teleporttolls.manager;

import org.bukkit.configuration.file.FileConfiguration;

import com.armeniumstudios.teleporttolls.TeleportTolls;

public class ConfigManager {
  private static int teleportRequestExpirationSeconds;
  private static double baseToll;
  private static double tollPerBlock;

  public static void refresh() {
    TeleportTolls.getInstance().reloadConfig();

    FileConfiguration config = TeleportTolls.getInstance().getConfig();
    teleportRequestExpirationSeconds = config.getInt("teleport_request_expiration_seconds", 120);
    baseToll = config.getDouble("base_toll", 5);
    tollPerBlock = config.getDouble("toll_per_block", 0.01);
  }

  public static int getTeleportRequestExpirationSeconds() {
    return teleportRequestExpirationSeconds;
  }

  public static double getBaseToll() {
    return baseToll;
  }

  public static double getTollPerBlock() {
    return tollPerBlock;
  }
}
