package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_TRANSACTION_TYPE_TABLE_NAME)
public class TransactionTypeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_TYPE_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_TYPE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_TRANSACTION_TYPE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "REMITTANCE_TYPE")
  private RemittanceType remittanceType;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType", cascade = CascadeType.ALL)
  private List<RemittanceChargeMappingEntity> remittanceChargeMappingEntities;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType", cascade = CascadeType.ALL)
  private List<RemittanceTransactionEntity> remittanceTransactionEntityList;

}
