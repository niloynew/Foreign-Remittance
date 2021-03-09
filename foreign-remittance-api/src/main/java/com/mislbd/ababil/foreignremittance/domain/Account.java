package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Account {

  private long id;
  private Long productId;
  private String productName;
  private String accountTitle;
  private String shadowAccountNumber;
  private String nostroAccountNumber;
  private String currencyCode;
  private Long bankId;
  private Long branchId;
  private LocalDate accountOpenDate;
  private BigDecimal balance;
  private BigDecimal blockAmount;
  private boolean active;
}
