package com.armeniumstudios.teleporttolls.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class PlayerCompleter implements TabCompleter {

  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }
    return new ArrayList<String>();
  }
}
