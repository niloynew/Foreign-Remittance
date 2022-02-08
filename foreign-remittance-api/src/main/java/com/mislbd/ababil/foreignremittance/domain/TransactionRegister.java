package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.transaction.api.transaction.model.AmountType;
import com.mislbd.transaction.api.transaction.model.IbtaInformation;
import com.mislbd.transaction.api.transaction.model.InstrumentType;
import com.mislbd.transaction.api.transaction.model.TransactionType;
import com.mislbd.transaction.api.transaction.repository.schema.TransactionEntryType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class TransactionRegister {

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
    private IbtaInformation ibtaInformation;
    private Long remittanceTransactionId;
    private boolean valid;
}
