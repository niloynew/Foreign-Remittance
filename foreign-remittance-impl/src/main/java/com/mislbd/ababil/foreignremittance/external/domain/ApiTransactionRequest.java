package com.mislbd.ababil.foreignremittance.external.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ApiTransactionRequest {

  private String requestId;
  private String referenceNumber;
  private LocalDate valueDate;
  private LocalDate transactionDate;
  private String initiatorModule;
  private Long initiatorBranchId;
  private List<ApiTransaction> apiTransactions;
  private String entryUserTerminal;
  private String verifyUser;
  private String verifyUserTerminal;
}
