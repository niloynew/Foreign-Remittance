package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_CHARGE_INFO_TABLE_NAME)
public class RemittanceChargeInformationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_CHARGE_INFO_ID_GEN")
  @SequenceGenerator(
      name = "ID_REMITTANCE_CHARGE_INFO_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_CHARGE_INFO_SEQUENCE_NAME)
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
}
