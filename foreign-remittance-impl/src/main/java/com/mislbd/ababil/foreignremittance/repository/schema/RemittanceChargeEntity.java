package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_CHARGE_TABLE_NAME)
public class RemittanceChargeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_CHARGE_SEQUENCE_NAME)
  private long id;

  @Column(name = "CHARGE_NAME", nullable = false, unique = true)
  private String chargeName;

  private String currencyCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "CHARGE_ACC_TYPE")
  private ChargeAccountType chargeAccountType;

  @Column(name = "CHARGE_ACC_CODE", nullable = false, length = 13)
  private String chargeAccountCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "VAT_ACC_TYPE")
  private ChargeAccountType vatAccountType;

  @Column(name = "VAT_ACC_CODE", nullable = false, length = 13)
  private String vatAccountCode;

  @Column(name = "IS_CHARGE_MODIFIABLE")
  private boolean chargeModifiable;

  @Column(name = "IS_VAT_MODIFIABLE")
  private boolean vatModifiable;

  @Column(name = "IS_SLAB_BASED")
  private boolean slabBased;

  @Column(name = "IS_FIXED_CHARGE")
  private boolean fixedCharge;

  @Column(name = "CHARGE_AMOUNT")
  private BigDecimal chargeAmount;

  @Column(name = "CHARGE_PERCENTAGE")
  private BigDecimal chargePercentage;

  @Column(name = "IS_FIXED_VAT")
  private boolean fixedVat;

  @Column(name = "VAT_AMOUNT")
  private BigDecimal vatAmount;

  @Column(name = "VAT_PERCENTAGE")
  private BigDecimal vatPercentage;

  @Column(name = "MIN_CHARGE_AMOUNT")
  private BigDecimal minimumChargeAmount;

  @Column(name = "MAX_CHARGE_AMOUNT")
  private BigDecimal maximumChargeAmount;

  @Column(name = "IS_ACTIVE")
  private boolean active;

  @OneToMany(mappedBy = "remittanceCharge", fetch = FetchType.LAZY)
  private List<RemittanceChargeSlabEntity> remittanceChargeSlabs;

  @OneToMany(mappedBy = "remittanceCharge", fetch = FetchType.LAZY)
  private List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities;

  @Transient private Boolean canModifyCharge;
}
