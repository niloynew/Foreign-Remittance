package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;

/** Created by hasan on 11/16/17. */
@Entity
@Table(
    name = "PROD_ACCOUNT_NUMBER_SEQUENCE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"branchId", "productId"}))
public class AccountNumberSequenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long branchId;
  private long productId;
  private long sequence;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBranchId() {
    return branchId;
  }

  public void setBranchId(long branchId) {
    this.branchId = branchId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getSequence() {
    return sequence;
  }

  public void setSequence(long sequence) {
    this.sequence = sequence;
  }
}
