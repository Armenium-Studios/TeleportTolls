package com.armeniumstudios.teleporttolls.model;

import org.bukkit.entity.Player;

public class TeleportApproval {
  private final Player approver;
  private final Player approvee;
  private final long expiresAt;

  public TeleportApproval(Player approver, Player approvee, long minutes) {
    this.approver = approver;
    this.approvee = approvee;
    this.expiresAt = minutes < 0 ? -1 : System.currentTimeMillis() + (minutes * 1000L * 60L);
  }

  public TeleportApproval(Player approver, Player approvee) {
    this(approver, approvee, -1);
  }


  public Player getApprover() {
    return this.approver;
  }


  public Player getApprovee() {
    return this.approvee;
  }


  public long getExpiresAt() {
    return this.expiresAt;
  }
}
