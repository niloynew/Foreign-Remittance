package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_BANK_INFO_TABLE_NAME)
public class BankInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_BANK_ID_GEN")
  @SequenceGenerator(
      name = "ID_REMITTANCE_BANK_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_REMITTANCE_BANK_SEQUENCE_NAME)
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
