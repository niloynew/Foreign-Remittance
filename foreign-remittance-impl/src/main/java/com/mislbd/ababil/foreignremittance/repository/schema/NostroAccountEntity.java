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
@Table(name = SchemaConstant.NOSTRO_ACCOUNT_TABLE_NAME)
public class NostroAccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "NOSTRO_ACCOUNT_ID_GEN")
  @SequenceGenerator(
      name = "NOSTRO_ACCOUNT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.NOSTRO_ACCOUNT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "NostroAccountNumber")
  private String nostroAccountNumber;

  @Column(name = "ShadowAccountNumber")
  private String shadowAccountNumber;

  @Column(name = "AccountName")
  private String name;

  @Column(name = "Currency")
  private String currencyCode;

  @Column(name = "AccountOpenDate")
  private LocalDate accOpenDate;

  @Column(name = "Balance")
  private BigDecimal balance;

  @Column(name = "NOSTRO_BANK")
  private Long bankId;

  @Column(name = "NOSTRO_BRANCH")
  private Long branchId;

  @Column(name = "IsActive")
  private boolean isActive;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private IDProductEntity product;
}
