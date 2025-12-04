package com.armeniumstudios.teleporttolls.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.armeniumstudios.teleporttolls.manager.LocaleManager;

public class TeleportTollsCompleter implements TabCompleter {

  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      return Arrays.asList("info", "language");
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("language")) {
        return LocaleManager.getLanguageList();
      }
    }
    return new ArrayList<String>();
  }
}
