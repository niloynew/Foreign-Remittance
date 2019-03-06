package com.mislbd.swift.service.mt103;

/** Created by user on 2/25/2016. */
public enum CodeListField59F {
  ONE("1"),
  TWO("2"),
  THREE("3");

  private final String text;

  /** @param text */
  private CodeListField59F(final String text) {
    this.text = text;
  }

  /* (non-Javadoc)
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return text;
  }

  public static CodeListField59F fromString(String text) {
    if (text != null) {
      for (CodeListField59F b : CodeListField59F.values()) {
        if (text.equalsIgnoreCase(b.toString())) {
          return b;
        }
      }
    }
    return null;
  }
}
