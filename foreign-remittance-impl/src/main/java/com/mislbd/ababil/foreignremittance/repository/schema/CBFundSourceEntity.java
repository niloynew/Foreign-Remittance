package com.mislbd.ababil.foreignremittance.repository.schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_CB_FUND_SOURCE_TABLE_NAME)
public class CBFundSourceEntity{

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

}
