package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.REMITTANCE_CHARGE_MAPPING_TABLE)
public class RemittanceChargeMappingEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_MAPPING_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_MAPPING_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.REMITTANCE_CHARGE_MAPPING_SEQUENCE_NAME)
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

  public long getId() {
    return id;
  }

  public RemittanceChargeMappingEntity setId(long id) {
    this.id = id;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceChargeMappingEntity setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public TransactionTypeEntity getTransactionType() {
    return transactionType;
  }

  public RemittanceChargeMappingEntity setTransactionType(TransactionTypeEntity transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  public RemittanceChargeEntity getRemittanceCharge() {
    return remittanceCharge;
  }

  public RemittanceChargeMappingEntity setRemittanceCharge(
      RemittanceChargeEntity remittanceCharge) {
    this.remittanceCharge = remittanceCharge;
    return this;
  }

  public boolean isChargeModifiable() {
    return chargeModifiable;
  }

  public RemittanceChargeMappingEntity setChargeModifiable(boolean chargeModifiable) {
    this.chargeModifiable = chargeModifiable;
    return this;
  }
}
