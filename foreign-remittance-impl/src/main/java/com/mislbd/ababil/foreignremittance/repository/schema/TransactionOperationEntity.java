package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_TRANSACTION_OPERATION_TABLE_NAME)
public class TransactionOperationEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_OPERATION_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_OPERATION_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_TRANSACTION_OPERATION_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Transaction_Type")
  private TransactionTypeEntity transactionTypeEntity;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionOperation")
  private List<RemittanceTransactionEntity> remittanceTransactionEntities;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "transactionOperation", cascade = CascadeType.ALL)
  private List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities;

  public long getId() {
    return id;
  }

  public TransactionOperationEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TransactionOperationEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public TransactionOperationEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public TransactionTypeEntity getTransactionTypeEntity() {
    return transactionTypeEntity;
  }

  public TransactionOperationEntity setTransactionTypeEntity(
      TransactionTypeEntity transactionTypeEntity) {
    this.transactionTypeEntity = transactionTypeEntity;
    return this;
  }

  public List<RemittanceTransactionEntity> getRemittanceTransactionEntities() {
    return remittanceTransactionEntities;
  }

  public TransactionOperationEntity setRemittanceTransactionEntities(
      List<RemittanceTransactionEntity> remittanceTransactionEntities) {
    this.remittanceTransactionEntities = remittanceTransactionEntities;
    return this;
  }

  public List<RemittanceChargeMappingEntity> getRemittanceChargeMappingEntities() {
    return remittanceChargeMappingEntities;
  }

  public TransactionOperationEntity setRemittanceChargeMappingEntities(
      List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities) {
    this.remittanceChargeMappingEntities = remittanceChargeMappingEntities;
    return this;
  }
}
