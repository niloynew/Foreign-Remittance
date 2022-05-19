package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_CATEGORY_TABLE_NAME)
public class RemittanceCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  private String name;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "REMITTANCE_TYPE")
  private RemittanceType remittanceType;
}
