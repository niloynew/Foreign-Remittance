package com.mislbd.ababil.foreignremittance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NostroReconcileDto {
  private String accountNo;
  private String currency;
  private String refToAccount;
  private String refOfServicingAccount;
  private String txnTYpe;
  private BigDecimal amount;
  private String advBranch;
  private String benefCustomer;
  private String benefInstitute;
  private String beneNameAndAddress;
  private String suppDetails;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate valueDate;

  private String remark;
  private boolean selected;
  private String dcMark;
  private long id;
  private String messageType;
  private String orderInstitute;
}
