package com.mislbd.ababil.foreignremittance.annotation.impl;

import com.mislbd.ababil.foreignremittance.annotation.SwiftAddress;
import com.mislbd.ababil.foreignremittance.utils.StringUtility;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SwiftAddressValidator implements ConstraintValidator<SwiftAddress, Object> {

  private int maxRow;
  private int maxColumn;

  @Override
  public void initialize(SwiftAddress constraintAnnotation) {
    maxRow = constraintAnnotation.expRow();
    maxColumn = constraintAnnotation.expColumn();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if (value != null) {
      String address = (String) value;
      String[] splitLines = StringUtility.getLines(address);
      if (splitLines.length > maxRow) {
        return false;
      }
      for (String s : splitLines) {
        if (s.length() > maxColumn) {
          return false;
        }
      }
    }
    return true;
  }
}
