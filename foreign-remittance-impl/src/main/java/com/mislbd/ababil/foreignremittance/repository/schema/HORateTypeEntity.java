package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.HO_RATE_TYPE_TABLE_NAME)
public class HORateTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "HO_RATE_TYPE_ID_GEN")
  @SequenceGenerator(
      name = "HO_RATE_TYPE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.HO_RATE_TYPE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  public long getId() {
    return id;
  }

  public HORateTypeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public HORateTypeEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public HORateTypeEntity setDescription(String description) {
    this.description = description;
    return this;
  }
}
