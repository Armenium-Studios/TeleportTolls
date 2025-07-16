package com.armeniumstudios.teleporttolls.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.armeniumstudios.teleporttolls.locale.LocaleManager;

public class LanguageCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return LocaleManager.getLanguageList();
        } else {
            return new ArrayList<String>();
        }
    }
}
