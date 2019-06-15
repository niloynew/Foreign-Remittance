package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.PAYMENT_PURPOSE_TABLE_NAME)
public class PaymentPurposeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE_LEVEL")
  private String level;

  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  // region <Getter and Setter>

  public long getId() {
    return id;
  }

  public PaymentPurposeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getLevel() {
    return level;
  }

  public PaymentPurposeEntity setLevel(String level) {
    this.level = level;
    return this;
  }

  public String getCode() {
    return code;
  }

  public PaymentPurposeEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public PaymentPurposeEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  // <end region>
}
