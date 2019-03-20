package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "ID_Transaction_Type")
public class TransactionTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
