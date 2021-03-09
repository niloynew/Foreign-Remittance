package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.SHADOW_ACCOUNT_TABLE_NAME)
public class ShadowAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "SHADOW_ACCOUNT_ID_GEN")
  @SequenceGenerator(
      name = "SHADOW_ACCOUNT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.SHADOW_ACCOUNT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE")
  private String number;

  @Column(name = "ACCOUNT_NAME")
  private String name;

  @Column(name = "NOSTRO_ACCOUNT_NUMBER")
  private String nostroAccountNumber;

  @Column(name = "NOSTRO_BANK")
  private Long bankId;

  @Column(name = "NOSTRO_BRANCH")
  private Long branchId;

  @Column(name = "BRANCH_ID")
  private Long ownerBranchId;

  @Column(name = "ACCOUNT_OPEN_DATE")
  private LocalDate accountOpenDate;

  @Column(name = "CURRENCY")
  private String currencyCode;

  @Column(name = "Balance")
  private BigDecimal balance;

  @Column(name = "BLOCK_AMOUNT")
  private BigDecimal blockAmount;

  @Column(name = "IsActive")
  private boolean isActive;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private IDProductEntity product;
}
