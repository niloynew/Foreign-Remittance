package com.mislbd.ababil.foreignremittance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExportRelatedRemittanceInformation {
  private String transactionReferenceNumber;
  private String internalReferenceNumber;
  private String salesContractNumber;
  private String arvNumber;
  private BigDecimal amount;
  private String currency;
  private Long customerId;
  private String applicantName;
  private String applicantCountry;
  private LocalDate valueDate;
  private String initiator;
  private String verifier;
}
