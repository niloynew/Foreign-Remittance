package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_BANK_TYPE_TABLE_NAME)
public class BankTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "BANK_TYPE_ID_GEN")
  @SequenceGenerator(
      name = "BANK_TYPE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_BANK_TYPE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  public long getId() {
    return id;
  }

  public BankTypeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public BankTypeEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public BankTypeEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public BankTypeEntity setDescription(String description) {
    this.description = description;
    return this;
  }
}
