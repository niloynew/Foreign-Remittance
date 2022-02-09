package com.mislbd.ababil.foreignremittance.repository.schema;

import com.mislbd.transaction.api.transaction.model.AmountType;
import com.mislbd.transaction.api.transaction.model.InstrumentType;
import com.mislbd.transaction.api.transaction.model.TransactionType;
import com.mislbd.transaction.api.transaction.repository.schema.TransactionEntryType;
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
@Table(name = SchemaConstant.ID_TRANSACTION_REGISTER_TABLE_NAME)
public class TransactionRegisterEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_REGISTER_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_REGISTER_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ID_TRANSACTION_REGISTER_SEQUENCE_NAME)
  private long id;

  private Integer setNumber;
  private String transactionTypeCode;
  private TransactionEntryType transactionEntryType;
  private TransactionType transactionType;
  private String accountNumber;
  private String accountType;
  private BigDecimal amountCCY;
  private BigDecimal amountRCY;
  private AmountType amountType;
  private BigDecimal exchangeRate;
  private Integer rateType;
  private String narration;
  private String instrumentNumber;
  private InstrumentType instrumentType;
  private LocalDate instrumentDate;
  private String accountCurrencyCode;
  private String currencyCode;
  private LocalDate valueDate;
  private Long voucherNumber;
  private String referenceNumber;
  private Long accountBranchId;
  private Long initiatorBranchId;
  private boolean ibtaRequired;
  private Long remittanceTransactionId;
  private boolean valid;
}
