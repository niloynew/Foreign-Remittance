package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_BANK_TYPE_TABLE_NAME)
public class BankTypeEntity {

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

  @Column(name = "MANDATORY", length = 1)
  private String mandatory;
}
