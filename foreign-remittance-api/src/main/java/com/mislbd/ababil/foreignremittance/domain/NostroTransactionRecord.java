package com.mislbd.ababil.foreignremittance.domain;

// import com.mislbd.ababil.foreignremittance.repository.schema.SwiftBankConfigurationEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class NostroTransactionRecord implements Serializable {

  private long id;

  private String accountNo;

  private String currency;

  private String refToAccount;

  private String refOfServicingAccount;

  private String txnType;

  private BigDecimal amount;

  private String advBranch;

  private String beneficiaryAccount;

  private String beneficiaryInstitute;

  private String beneficiaryName;

  private String beneficiaryAddress;

  private String supplementaryDetails;

  private LocalDate valueDate;

  private String remark;

  private boolean selected;

  private String dcMark;

  private String messageType;

  private String messageText;

  private String applicantName;

  private String applicantAddress;

  private String applicantAccount;
}
