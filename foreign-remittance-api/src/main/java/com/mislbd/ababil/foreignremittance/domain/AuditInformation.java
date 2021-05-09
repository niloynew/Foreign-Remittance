package com.mislbd.ababil.foreignremittance.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AuditInformation {

  private String entryUser;
  private String entryTerminal;
  private LocalDate entryDate;
  private String verifyUser;
  private String verifyTerminal;
  private Integer userBranch;
  private String processId;
  private Long globalTxnNumber;
}
