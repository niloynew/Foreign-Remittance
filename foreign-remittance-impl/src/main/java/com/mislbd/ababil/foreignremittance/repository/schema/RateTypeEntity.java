package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.CLIENT_RATE_TYPE_TABLE_NAME)
public class RateTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "CLIENT_RATE_TYPE_ID_GEN")
  @SequenceGenerator(
      name = "CLIENT_RATE_TYPE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.CLIENT_RATE_TYPE_SEQUENCE_NAME)
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

  public RateTypeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public RateTypeEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public RateTypeEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public RateTypeEntity setDescription(String description) {
    this.description = description;
    return this;
  }
}
