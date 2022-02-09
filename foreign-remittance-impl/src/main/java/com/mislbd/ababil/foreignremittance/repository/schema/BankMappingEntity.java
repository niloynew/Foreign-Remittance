package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.swift.broker.model.raw.SelectOptions;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.ID_REMITTANCE_BANK_MAPPING_TABLE_NAME)
public class BankMappingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_REMITTANCE_BANK_MAPPING_ID_GEN")
  @SequenceGenerator(
      name = "ID_REMITTANCE_BANK_MAPPING_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_REMITTANCE_BANK_MAPPING_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "Option")
  private SelectOptions option;

  @Column(name = "BANK_TYPE")
  private BankType bankType;

  @Column(name = "PARTY_IDENTIFIER")
  private String partyIdentifier;

  @Column(name = "IDENTIFIER_CODE")
  private String identifierCode;

  @Column(name = "LOCATION")
  private String location;

  @Column(name = "NAME_AND_ADDRESS")
  private String nameAndAddress;

  @ManyToOne
  @JoinColumn(name = "Remittance_Tnx_Id")
  private RemittanceTransactionEntity remittanceTransaction;
}
