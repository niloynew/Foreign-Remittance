package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
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

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "transactionTypeEntity", cascade = CascadeType.ALL)
  private List<TransactionOperationEntity> transactionOperationEntities;

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

  public List<TransactionOperationEntity> getTransactionOperationEntities() {
    return transactionOperationEntities;
  }

  public TransactionTypeEntity setTransactionOperationEntities(
      List<TransactionOperationEntity> transactionOperationEntities) {
    this.transactionOperationEntities = transactionOperationEntities;
    return this;
  }
}
