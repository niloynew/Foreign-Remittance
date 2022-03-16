package com.mislbd.ababil.foreignremittance.utils;

import java.util.List;

public class StringUtility {

  public static String[] getLines(String str) {
    if (str == null) return null;
    if (str.length() <= 1) return new String[] {str};
    return str.split("\\r?\\n");
  }

  public static boolean isNullOrEmpty(String val) {
    return val == null || val.isEmpty();
  }

  public static boolean isNullOrEmpty(List list) {
    return list == null || list.isEmpty();
  }

  public static void addIfNotNullOrEmpty(
      StringBuilder buffer, String val, String prefix, String suffix) {
    if (isNullOrEmpty(val)) return;
    if (!isNullOrEmpty(prefix)) buffer.append(prefix);
    buffer.append(val);
    if (!isNullOrEmpty(suffix)) buffer.append(suffix);
  }

  public static void addIfNotNullOrEmpty(StringBuilder buffer, String val, String prefix) {
    addIfNotNullOrEmpty(buffer, val, prefix, null);
  }

  public static void addIfNotNullOrEmpty(StringBuilder buffer, String val) {
    addIfNotNullOrEmpty(buffer, val, null, null);
  }
}
