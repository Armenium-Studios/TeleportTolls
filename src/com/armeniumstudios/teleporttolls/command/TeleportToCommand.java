package com.armeniumstudios.teleporttolls.command;

import com.armeniumstudios.teleporttolls.TeleportTolls;
import com.armeniumstudios.teleporttolls.manager.LocaleManager;
import com.armeniumstudios.teleporttolls.util.TollUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportToCommand implements CommandExecutor {

  TeleportTolls plugin;

  public TeleportToCommand() {
    plugin = TeleportTolls.getInstance();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + LocaleManager.getMessage(sender, "general.players_only"));
      return true;
    }

    Player player = (Player) sender;

    if (args.length == 1) {
      Player playerTo = Bukkit.getPlayer(args[0]);
      if (playerTo == null) {
        sender.sendMessage(
            ChatColor.RED + LocaleManager.getMessage(sender, "general.invalid_player"));
        return true;
      }

      TollUtilities.teleportWithToll(player, playerTo);

      return true;
    }

    return false;
  }
}
