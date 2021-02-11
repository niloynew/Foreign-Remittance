package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.BANK_CONFIGURATION)
public class BankConfiguration {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "BANKTYPE_ID")
  private long bankTypeId;

  @Column(name = "BANK_ID")
  private long bankId;

  @Column(name = "BANKTYPE_NAME")
  private String bankTypeName;

  @Column(name = "BANK_NAME")
  private String bankName;

  @Column(name = "SWIFT_CODE")
  private String swiftCode;

  @Column(name = "BRANCH_ID")
  private long branchId;

  @Column(name = "BRANCH_NAME")
  private String branchName;

  @ManyToOne @JoinColumn private NostroTransactionEntity nostroTransaction;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBankTypeId() {
    return bankTypeId;
  }

  public void setBankTypeId(long bankTypeId) {
    this.bankTypeId = bankTypeId;
  }

  public long getBankId() {
    return bankId;
  }

  public void setBankId(long bankId) {
    this.bankId = bankId;
  }

  public String getBankTypeName() {
    return bankTypeName;
  }

  public void setBankTypeName(String bankTypeName) {
    this.bankTypeName = bankTypeName;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public void setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
  }

  public long getBranchId() {
    return branchId;
  }

  public void setBranchId(long branchId) {
    this.branchId = branchId;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public NostroTransactionEntity getNostroTransactionEntity() {
    return nostroTransaction;
  }

  public void setNostroTransactionEntity(NostroTransactionEntity nostroTransactionEntity) {
    this.nostroTransaction = nostroTransactionEntity;
  }
}
