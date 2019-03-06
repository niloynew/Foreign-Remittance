package com.mislbd.swift.service.mt103;

/** Created by user on 1/3/2016. */
public enum CodeListField50F {
  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8");

  private final String text;

  /** @param text */
  private CodeListField50F(final String text) {
    this.text = text;
  }

  /* (non-Javadoc)
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return text;
  }

  public static CodeListField50F fromString(String text) {
    if (text != null) {
      for (CodeListField50F b : CodeListField50F.values()) {
        if (text.equalsIgnoreCase(b.toString())) {
          return b;
        }
      }
    }
    return null;
  }
}
