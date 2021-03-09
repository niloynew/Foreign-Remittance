package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_BANK_MAPPING_TABLE_NAME)
public class RemittanceTransactionBankMappingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_BANK_MAPPING_ID_GEN")
  @SequenceGenerator(
      name = "ID_REMITTANCE_BANK_MAPPING_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_REMITTANCE_BANK_MAPPING_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "Bank_Type_Id")
  private Long bankTypeId;

  @Column(name = "BIC_Code")
  private String swiftCode;

  @ManyToOne
  @JoinColumn(name = "Remittance_Tnx_Id")
  private RemittanceTransactionEntity remittanceTransaction;
}
