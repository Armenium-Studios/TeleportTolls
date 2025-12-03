package com.armeniumstudios.teleporttolls.util;

import com.armeniumstudios.teleporttolls.ConfigManager;
import com.armeniumstudios.teleporttolls.TeleportTolls;
import com.armeniumstudios.teleporttolls.locale.LocaleManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TollUtilities {
  public static boolean canIgnoreTolls(Player player) {
    return player.hasPermission("teleporttolls.ignoretolls");
  }

  public static double calculateToll(Location from, Location to) {
    double toll = ConfigManager.getBaseToll();

    toll += from.distance(to) * ConfigManager.getTollPerBlock();

    return PluginUtilities.round(toll, 2);
  }

  public static double calculateToll(Player from, Player to) {
    return calculateToll(from.getLocation(), to.getLocation());
  }

  public static boolean teleportWithToll(Player player, Player to) {
    double bal = TeleportTolls.getEconomy().getBalance(player);
    double toll = calculateToll(player, to);
    if (bal >= toll) {
      TeleportTolls.getEconomy().withdrawPlayer(player, toll);
      player.teleport(to);
      player.sendMessage(
          ChatColor.GREEN
              + LocaleManager.getMessage(
                  player,
                  "tp.success",
                  to.getDisplayName() + ChatColor.GREEN,
                  TollUtilities.toCurrencyString(toll)));
      return true;
    } else {
      player.sendMessage(
          ChatColor.RED
              + LocaleManager.getMessage(
                  player,
                  "tp.cannot_afford",
                  TollUtilities.toCurrencyString(toll),
                  TollUtilities.toCurrencyString(bal)));
      return false;
    }
  }

  public static String toCurrencyString(double amount) {
    return (amount == 1.0
            ? TeleportTolls.getEconomy().currencyNameSingular()
            : TeleportTolls.getEconomy().currencyNamePlural())
        + amount;
  }
}
