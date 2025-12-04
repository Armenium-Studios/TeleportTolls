package com.armeniumstudios.teleporttolls.command;

import com.armeniumstudios.teleporttolls.TeleportTolls;
import com.armeniumstudios.teleporttolls.manager.LocaleManager;
import com.armeniumstudios.teleporttolls.manager.PlayerConfigManager;
import com.armeniumstudios.teleporttolls.model.TeleportApproval;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAllowCommand implements CommandExecutor {

  TeleportTolls plugin;

  public TeleportAllowCommand() {
    plugin = TeleportTolls.getInstance();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + LocaleManager.getMessage(sender, "general.players_only"));
      return true;
    }

    Player player = (Player) sender;

    if (args.length == 1 || args.length == 2) {
      Player playerFrom = Bukkit.getPlayer(args[0]);
      if (playerFrom == null) {
        sender.sendMessage(
            ChatColor.RED + LocaleManager.getMessage(sender, "general.invalid_player"));
        return true;
      } else if (playerFrom.equals(player)) {
        sender.sendMessage(
            ChatColor.RED + LocaleManager.getMessage(sender, "general.invalid_self"));
        return true;
      }

      long minutes = -1;
      if (args.length == 2) {
        try {
          minutes = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          sender.sendMessage(
              ChatColor.RED + LocaleManager.getMessage(sender, "tp.invalid_minutes"));
          return true;
        }
      }

      PlayerConfigManager.saveApproval(new TeleportApproval(player, playerFrom, minutes));
      if (minutes < 0) {
        sender.sendMessage(
            ChatColor.GREEN
                + LocaleManager.getMessage(
                    sender, "tp.approved", playerFrom.getDisplayName() + ChatColor.GREEN));
      } else {
        sender.sendMessage(
            ChatColor.GREEN
                + LocaleManager.getMessage(
                    sender,
                    "tp.approved_with_length",
                    playerFrom.getDisplayName() + ChatColor.GREEN,
                    minutes));
      }

      return true;
    }

    return false;
  }
}
