package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_CHARGE_INFO_TABLE_NAME)
public class RemittanceChargeInformationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_CHARGE_INFO_ID_GEN")
  @SequenceGenerator(
      name = "ID_REMITTANCE_CHARGE_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_REMITTANCE_CHARGE_INFO_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Enumerated(EnumType.STRING)
  private RemittanceType remittanceType;

  @Column(name = "CHARGE_ID")
  private long chargeId;

  @Column(name = "CHARGE_NAME")
  private String chargeName;

  @Column(name = "CHARGE_AMOUNT")
  private BigDecimal chargeAmount;

  @Column(name = "IS_CHARGE_MODIFIABLE")
  private boolean chargeModifiable;

  @Column(name = "IS_VAT_MODIFIABLE")
  private boolean vatModifiable;

  @Column(name = "CHARGE_AMOUNT_AFTER_WAIVED")
  private BigDecimal chargeAmountAfterWaived;

  @Column(name = "VAT_AMOUNT_AFTER_WAIVED")
  private BigDecimal vatAmountAfterWaived;

  @Column(name = "VAT_AMOUNT")
  private BigDecimal vatAmount;

  @Column(name = "CURRENCY")
  private String currency;

  @ManyToOne
  @JoinColumn(name = "Remittance_Tnx_Id")
  private RemittanceTransactionEntity remittanceTransaction;

  public long getId() {
    return id;
  }

  public RemittanceChargeInformationEntity setId(long id) {
    this.id = id;
    return this;
  }

  public long getChargeId() {
    return chargeId;
  }

  public RemittanceChargeInformationEntity setChargeId(long chargeId) {
    this.chargeId = chargeId;
    return this;
  }

  public String getChargeName() {
    return chargeName;
  }

  public RemittanceChargeInformationEntity setChargeName(String chargeName) {
    this.chargeName = chargeName;
    return this;
  }

  public BigDecimal getChargeAmount() {
    return chargeAmount;
  }

  public RemittanceChargeInformationEntity setChargeAmount(BigDecimal chargeAmount) {
    this.chargeAmount = chargeAmount;
    return this;
  }

  public RemittanceType getRemittanceType() {
    return remittanceType;
  }

  public RemittanceChargeInformationEntity setRemittanceType(RemittanceType remittanceType) {
    this.remittanceType = remittanceType;
    return this;
  }

  public boolean isChargeModifiable() {
    return chargeModifiable;
  }

  public RemittanceChargeInformationEntity setChargeModifiable(boolean chargeModifiable) {
    this.chargeModifiable = chargeModifiable;
    return this;
  }

  public boolean isVatModifiable() {
    return vatModifiable;
  }

  public RemittanceChargeInformationEntity setVatModifiable(boolean vatModifiable) {
    this.vatModifiable = vatModifiable;
    return this;
  }

  public BigDecimal getChargeAmountAfterWaived() {
    return chargeAmountAfterWaived;
  }

  public RemittanceChargeInformationEntity setChargeAmountAfterWaived(
      BigDecimal chargeAmountAfterWaived) {
    this.chargeAmountAfterWaived = chargeAmountAfterWaived;
    return this;
  }

  public BigDecimal getVatAmountAfterWaived() {
    return vatAmountAfterWaived;
  }

  public RemittanceChargeInformationEntity setVatAmountAfterWaived(
      BigDecimal vatAmountAfterWaived) {
    this.vatAmountAfterWaived = vatAmountAfterWaived;
    return this;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public RemittanceChargeInformationEntity setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public RemittanceChargeInformationEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public RemittanceTransactionEntity getRemittanceTransaction() {
    return remittanceTransaction;
  }

  public RemittanceChargeInformationEntity setRemittanceTransaction(
      RemittanceTransactionEntity remittanceTransaction) {
    this.remittanceTransaction = remittanceTransaction;
    return this;
  }
}
