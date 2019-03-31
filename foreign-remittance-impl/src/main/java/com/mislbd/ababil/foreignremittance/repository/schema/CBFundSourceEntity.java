package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_CB_FUND_SOURCE_TABLE_NAME)
public class CBFundSourceEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "CB_FUND_SOURCE_ID_GEN")
  @SequenceGenerator(
      name = "CB_FUND_SOURCE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_CB_FUND_SOURCE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  // region <Getter and Setter>

  public long getId() {
    return id;
  }

  public CBFundSourceEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CBFundSourceEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public CBFundSourceEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  // <end region>
}
