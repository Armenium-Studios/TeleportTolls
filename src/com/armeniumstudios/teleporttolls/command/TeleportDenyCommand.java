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

public class TeleportDenyCommand implements CommandExecutor {

  TeleportTolls plugin;

  public TeleportDenyCommand() {
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

      PlayerConfigManager.removeApproval(new TeleportApproval(player, playerFrom));
      sender.sendMessage(
          ChatColor.GREEN
              + LocaleManager.getMessage(
                  sender, "tp.denied", playerFrom.getDisplayName() + ChatColor.GREEN));

      return true;
    }

    return false;
  }
}
