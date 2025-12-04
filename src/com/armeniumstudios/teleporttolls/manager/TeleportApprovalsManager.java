package com.armeniumstudios.teleporttolls.manager;

import com.armeniumstudios.teleporttolls.TeleportTolls;
import com.armeniumstudios.teleporttolls.model.TeleportApproval;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class TeleportApprovalsManager {
  private static final String fileName = "teleport-approvals.yml";

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

  public void saveApproval(TeleportApproval approval) {
    if (!config.contains("players")) {
      config.createSection("players");
    }

    String approverSectionDir = "players." + approval.getApprover().getUniqueId();
    if (!config.contains(approverSectionDir)) {
      config.createSection(approverSectionDir);
    }

    ConfigurationSection approverSection = config.getConfigurationSection(approverSectionDir);
    if (!approverSection.contains(approval.getApprovee().getUniqueId().toString())) {
      approverSection.set(approval.getApprovee().getUniqueId().toString(), approval.getExpiresAt());
    }
    
    save();
  }

  public void removeApproval(TeleportApproval approval) {
    String approveeDir = "players." + approval.getApprover().getUniqueId() + "." + approval.getApprovee().getUniqueId();
    if (config.contains(approveeDir)) {
      config.set(approveeDir, null);
      save();
    }
  }

  public boolean isApprovalActive(TeleportApproval approval) {
    String approveeDir = "players." + approval.getApprover().getUniqueId() + "." + approval.getApprovee().getUniqueId();
    if (config.contains(approveeDir)) {
      long timeTill = config.getLong(approveeDir);
      if (timeTill < 0) {
        return true;
      } else {
        return System.currentTimeMillis() < timeTill;
      }
    } else {
      return false;
    }
  }

  public static void save() {
    try {
      config.save(fileName);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
