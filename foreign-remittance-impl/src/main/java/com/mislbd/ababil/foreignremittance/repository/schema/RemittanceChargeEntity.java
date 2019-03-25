package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.REMITTANCE_CHARGE_TABLE_NAME)
public class RemittanceChargeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "REMITTANCE_CHARGE_ID_GEN")
  @SequenceGenerator(
      name = "REMITTANCE_CHARGE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.REMITTANCE_CHARGE_SEQUENCE_NAME)
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

  public long getId() {
    return id;
  }

  public RemittanceChargeEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getChargeName() {
    return chargeName;
  }

  public RemittanceChargeEntity setChargeName(String chargeName) {
    this.chargeName = chargeName;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public RemittanceChargeEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public ChargeAccountType getChargeAccountType() {
    return chargeAccountType;
  }

  public RemittanceChargeEntity setChargeAccountType(ChargeAccountType chargeAccountType) {
    this.chargeAccountType = chargeAccountType;
    return this;
  }

  public String getChargeAccountCode() {
    return chargeAccountCode;
  }

  public RemittanceChargeEntity setChargeAccountCode(String chargeAccountCode) {
    this.chargeAccountCode = chargeAccountCode;
    return this;
  }

  public ChargeAccountType getVatAccountType() {
    return vatAccountType;
  }

  public RemittanceChargeEntity setVatAccountType(ChargeAccountType vatAccountType) {
    this.vatAccountType = vatAccountType;
    return this;
  }

  public String getVatAccountCode() {
    return vatAccountCode;
  }

  public RemittanceChargeEntity setVatAccountCode(String vatAccountCode) {
    this.vatAccountCode = vatAccountCode;
    return this;
  }

  public boolean isSlabBased() {
    return slabBased;
  }

  public RemittanceChargeEntity setSlabBased(boolean slabBased) {
    this.slabBased = slabBased;
    return this;
  }

  public boolean isFixedCharge() {
    return fixedCharge;
  }

  public RemittanceChargeEntity setFixedCharge(boolean fixedCharge) {
    this.fixedCharge = fixedCharge;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceChargeEntity setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public BigDecimal getChargePercentage() {
    return chargePercentage;
  }

  public RemittanceChargeEntity setChargePercentage(BigDecimal chargePercentage) {
    this.chargePercentage = chargePercentage;
    return this;
  }

  public boolean isFixedVat() {
    return fixedVat;
  }

  public RemittanceChargeEntity setFixedVat(boolean fixedVat) {
    this.fixedVat = fixedVat;
    return this;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public RemittanceChargeEntity setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
    return this;
  }

  public BigDecimal getVatPercentage() {
    return vatPercentage;
  }

  public RemittanceChargeEntity setVatPercentage(BigDecimal vatPercentage) {
    this.vatPercentage = vatPercentage;
    return this;
  }

  public BigDecimal getMinimumChargeAmount() {
    return minimumChargeAmount;
  }

  public RemittanceChargeEntity setMinimumChargeAmount(BigDecimal minimumChargeAmount) {
    this.minimumChargeAmount = minimumChargeAmount;
    return this;
  }

  public BigDecimal getMaximumChargeAmount() {
    return maximumChargeAmount;
  }

  public RemittanceChargeEntity setMaximumChargeAmount(BigDecimal maximumChargeAmount) {
    this.maximumChargeAmount = maximumChargeAmount;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public RemittanceChargeEntity setActive(boolean active) {
    this.active = active;
    return this;
  }

  public List<RemittanceChargeSlabEntity> getRemittanceChargeSlabs() {
    return remittanceChargeSlabs;
  }

  public RemittanceChargeEntity setRemittanceChargeSlabs(
      List<RemittanceChargeSlabEntity> remittanceChargeSlabs) {
    this.remittanceChargeSlabs = remittanceChargeSlabs;
    return this;
  }

  public Boolean getCanModifyCharge() {
    return canModifyCharge;
  }

  public RemittanceChargeEntity setCanModifyCharge(Boolean canModifyCharge) {
    this.canModifyCharge = canModifyCharge;
    return this;
  }

  public List<RemittanceChargeMappingEntity> getRemittanceChargeMappingEntities() {
    return remittanceChargeMappingEntities;
  }

  public RemittanceChargeEntity setRemittanceChargeMappingEntities(
      List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities) {
    this.remittanceChargeMappingEntities = remittanceChargeMappingEntities;
    return this;
  }
}
