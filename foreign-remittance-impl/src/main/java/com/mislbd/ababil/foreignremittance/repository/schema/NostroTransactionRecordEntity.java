package com.mislbd.ababil.foreignremittance.repository.schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.NOSTRO_TRANSACTION_RECORD_TABLE_NAME)
public class NostroTransactionRecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NOSTRO_TRANSACTION_RECORD_ID_GEN")
  @SequenceGenerator(
      name = "NOSTRO_TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.NOSTRO_TRANSACTION_RECORD_SEQUENCE_NAME)
  private long id;

  @Column(name = "ACCOUNT_NO")
  private String accountNo;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "REF_TO_ACCOUNT")
  private String refToAccount;

  @Column(name = "REF_OF_SERVICING_ACCOUNT")
  private String refOfServicingAccount;

  @Column(name = "TXNTYPE")
  private String txnType;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "ADV_BRANCH")
  private String advBranch;

  @Column(name = "BENEF_ACCOUNT")
  private String beneficiaryAccount;

  @Column(name = "BENEF_INSTITUTE")
  private String beneficiaryInstitute;

  @Column(name = "BENEF_NAME")
  private String beneficiaryName;

  @Column(name = "BENEF_ADDRESS")
  private String beneficiaryAddress;

  @Column(name = "SUPP_DETAILS")
  private String supplementaryDetails;

  @Column(name = "VALUE_DATE")
  private LocalDate valueDate;

  @Column(name = "REMARK")
  private String remark;

  @Column(name = "SELECTED")
  private boolean selected;

  @Column(name = "DC_MARK")
  private String dcMark;

  @Column(name = "MESSAGE_TYPE")
  private String messageType;

  @Lob
  @Column(name = "MSG")
  private String messageText;

  @Column(name = "APPLICANT")
  private String applicantName;

  @Column(name = "APPLICANT_ADDRESS")
  private String applicantAddress;

  @Column(name = "APPLICANT_ACCOUNT")
  private String applicantAccount;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "nostroTransaction")
  @Fetch(FetchMode.SUBSELECT)
  private List<SwiftBankConfigurationEntity> bankInformation;
}
