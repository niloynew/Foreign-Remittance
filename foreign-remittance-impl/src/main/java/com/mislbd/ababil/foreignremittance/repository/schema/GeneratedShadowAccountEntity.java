package com.mislbd.ababil.foreignremittance.repository.schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.SHADOW_ACCOUNT_NUMBER)
public class GeneratedShadowAccountEntity {
  @Id private long id;
  private long branchId;
  private long productId;
  private String accountNumber;
  private boolean reserved;
  private LocalDateTime reservationEndsOn;
  private String reservedBy;
}
