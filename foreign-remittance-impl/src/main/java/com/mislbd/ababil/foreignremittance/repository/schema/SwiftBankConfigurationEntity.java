package com.mislbd.ababil.foreignremittance.repository.schema;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_SWIFT_BANK_CONFIGURATION_TABLE_NAME)
public class SwiftBankConfigurationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_SWIFT_BANK_CONFIGURATION_ID_GEN")
  @SequenceGenerator(
      name = "ID_SWIFT_BANK_CONFIGURATION_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_SWIFT_BANK_CONFIGURATION_SEQUENCE_NAME)
  private long id;

  @Column(name = "BANKTYPE_ID")
  private long bankTypeId;

  @Column(name = "BANK_ID")
  private long bankId;

  @Column(name = "BANKTYPE_NAME")
  private String bankTypeName;

  @Column(name = "BANK_NAME")
  private String bankName;

  @Column(name = "SWIFT_CODE")
  private String swiftCode;

  @Column(name = "BRANCH_ID")
  private long branchId;

  @Column(name = "BRANCH_NAME")
  private String branchName;

  @ManyToOne @JoinColumn private NostroTransactionEntity nostroTransaction;
}
