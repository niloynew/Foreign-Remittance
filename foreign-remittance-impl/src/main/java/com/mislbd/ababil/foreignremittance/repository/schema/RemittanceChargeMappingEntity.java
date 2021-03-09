package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_CHARGE_MAPPING_TABLE)
public class RemittanceChargeMappingEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_MAPPING_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_MAPPING_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_CHARGE_MAPPING_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "REMITTANCE_TYPE")
  private RemittanceType remittanceType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TXN_TYPE_ID")
  private TransactionTypeEntity transactionType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CHARGE_ID")
  private RemittanceChargeEntity remittanceCharge;

  @Column(name = "CHARGE_MODIFIABLE")
  private boolean chargeModifiable;

  @Column(name = "VAT_MODIFIABLE")
  private boolean vatModifiable;
}
