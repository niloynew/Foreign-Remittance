package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_TRANSACTION_TYPE_TABLE_NAME)
public class TransactionTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_TYPE_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_TYPE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_TRANSACTION_TYPE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "REMITTANCE_TYPE")
  private RemittanceType remittanceType;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType", cascade = CascadeType.ALL)
  private List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType", cascade = CascadeType.ALL)
  private List<RemittanceTransactionEntity> remittanceTransactionEntityList;

  public long getId() {
    return id;
  }

  public TransactionTypeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public TransactionTypeEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public TransactionTypeEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public TransactionTypeEntity setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public List<RemittanceChargeMappingEntity> getRemittanceChargeMappingEntities() {
    return remittanceChargeMappingEntities;
  }

  public TransactionTypeEntity setRemittanceChargeMappingEntities(
      List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities) {
    this.remittanceChargeMappingEntities = remittanceChargeMappingEntities;
    return this;
  }

  public List<RemittanceTransactionEntity> getRemittanceTransactionEntityList() {
    return remittanceTransactionEntityList;
  }

  public TransactionTypeEntity setRemittanceTransactionEntityList(
      List<RemittanceTransactionEntity> remittanceTransactionEntityList) {
    this.remittanceTransactionEntityList = remittanceTransactionEntityList;
    return this;
  }
}
