package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(
    name = SchemaConstant.SHADOW_ACCOUNT_NUMBER_SEQUENCE_NAME,
    uniqueConstraints = @UniqueConstraint(columnNames = {"branchId", "productId"}))
public class ShadowAccountNumberSequenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long branchId;
  private long productId;
  private long sequence;
}
