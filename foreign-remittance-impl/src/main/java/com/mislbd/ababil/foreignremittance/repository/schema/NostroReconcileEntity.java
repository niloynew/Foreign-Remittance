package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.NOSTRO_RECONCILE)
public class NostroReconcileEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "ACCOUNT_NO")
  private String accountNo;

  private String currency;

  @Column(name = "REF_TO_ACCOUNT")
  private String referenceToAccount;
  private String referenceOfServicingAccount;

  @Column(name = "TXNTYPE")
  private String txnType;

  private BigDecimal amount;
  private String advisingBranch;
  private String beneficiaryCustomer;
  private String beneficiaryInstitute;
  private String beneficiaryNameAndAddress;
  private String suppDetails;
  private LocalDate valueDate;
  private String remark;
  private boolean selected;
  private String dcMark;
  private String messageType;


}
