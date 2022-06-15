package com.mislbd.ababil.foreignremittance.external.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionQueryResponse {
  private String voucherNumber;
  private QueryTransactionStatus transactionStatus;
}
