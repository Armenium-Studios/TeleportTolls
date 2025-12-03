package com.armeniumstudios.teleporttolls.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PluginUtilities {
  private static final String DEFAULT_LANGUAGE = "en_US";

  public static void init() {
    // Init
  }

  public static String getDefaultLanguage() {
    return DEFAULT_LANGUAGE;
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
