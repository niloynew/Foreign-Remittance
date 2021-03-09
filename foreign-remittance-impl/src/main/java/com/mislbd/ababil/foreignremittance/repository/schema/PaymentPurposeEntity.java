package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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
}
