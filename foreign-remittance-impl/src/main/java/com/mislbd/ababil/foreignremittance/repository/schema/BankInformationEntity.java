package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;

@Entity
@Table(name = "Remittance_Bank_Info")
public class BankInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "Bank_Type_Id")
  private Long bankTypeId;

  @Column(name = "BIC_Code")
  private String swiftCode;

  @ManyToOne
  @JoinColumn(name = "Remittance_Tnx_Id")
  private RemittanceTransactionEntity remittanceTransaction;

  public long getId() {
    return id;
  }

  public BankInformationEntity setId(long id) {
    this.id = id;
    return this;
  }

  public Long getBankTypeId() {
    return bankTypeId;
  }

  public BankInformationEntity setBankTypeId(Long bankTypeId) {
    this.bankTypeId = bankTypeId;
    return this;
  }

  public String getSwiftCode() {
    return swiftCode;
  }

  public BankInformationEntity setSwiftCode(String swiftCode) {
    this.swiftCode = swiftCode;
    return this;
  }

  public RemittanceTransactionEntity getRemittanceTransaction() {
    return remittanceTransaction;
  }

  public BankInformationEntity setRemittanceTransaction(
      RemittanceTransactionEntity remittanceTransaction) {
    this.remittanceTransaction = remittanceTransaction;
    return this;
  }
}
