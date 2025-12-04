package com.armeniumstudios.teleporttolls.manager;

import com.armeniumstudios.teleporttolls.TeleportTolls;
import com.armeniumstudios.teleporttolls.model.TeleportApproval;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerConfigManager {
  private static final String fileName = "players.yml";

  private static File file;
  private static YamlConfiguration config;

  public static void init() {
    file = new File(TeleportTolls.getInstance().getDataFolder(), fileName);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    config = YamlConfiguration.loadConfiguration(file);
  }

  public static void saveApproval(TeleportApproval approval) {
    config.set(getApproveeDir(approval), approval.getExpiresAt());
    save();
  }

  public static void removeApproval(TeleportApproval approval) {
    String approveeDir = getApproveeDir(approval);
    if (config.contains(approveeDir)) {
      config.set(approveeDir, null);
      save();
    }
  }

  public static boolean isApprovalActive(TeleportApproval approval) {
    String approveeDir = getApproveeDir(approval);
    if (config.contains(approveeDir)) {
      long timeTill = config.getLong(approveeDir);
      if (timeTill < 0) {
        return true;
      } else {
        if (System.currentTimeMillis() < timeTill) {
          return true;
        } else {
          config.set(approveeDir, null);
          return false;
        }
      }
    } else {
      return false;
    }
  }

  private static String getApproveeDir(TeleportApproval approval) {
    return "players." + approval.getApprover().getUniqueId() + ".approvals." + approval.getApprovee().getUniqueId();
  }

  public static void save() {
    try {
      config.save(file);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
