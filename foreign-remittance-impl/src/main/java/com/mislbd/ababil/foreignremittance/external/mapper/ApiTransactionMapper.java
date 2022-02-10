package com.mislbd.ababil.foreignremittance.external.mapper;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.external.domain.ApiTransaction;
import com.mislbd.ababil.foreignremittance.external.domain.ApiTransactionRequest;
import com.mislbd.ababil.foreignremittance.external.domain.TransactionJournalType;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.transaction.api.transaction.repository.schema.TransactionEntryType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ApiTransactionMapper {

  public ResultMapper<List<CbsTemplateTransaction>, ApiTransactionRequest>
      cbsTransactionToApiRequest() {
    return transactions ->
        new ApiTransactionRequest()
            .setApiTransactions(
                transactions
                    .stream()
                    .map(
                        txn ->
                            new ApiTransaction()
                                .setSetNumber(txn.getSetNumber())
                                .setAccountBranchId(null)
                                .setAccountCurrency(txn.getAccountCurrencyCode())
                                .setAccountNumber(txn.getAccountNumber())
                                .setAccountType(txn.getAccountType())
                                .setAdjustmentRefId(txn.getAdjReferenceId())
                                .setAmountCCY(txn.getAmountCCY())
                                .setAmountRCY(txn.getAmountRCY())
                                .setAmountType(txn.getAmountType())
                                .setExchangeRate(txn.getExchangeRate())
                                .setIbtaInformation(txn.getIbtaInformation())
                                .setInstrumentNumber(txn.getInstrumentNumber())
                                .setInstrumentDate(txn.getInstrumentDate())
                                .setInstrumentType(txn.getInstrumentType())
                                .setNarration(txn.getNarration())
                                .setRateType(txn.getRateType())
                                .setRefCurrencyCode(txn.getCurrencyCode())
                                .setTransactionJournalType(
                                    txn.getTransactionEntryType().equals(TransactionEntryType.Debit)
                                        ? TransactionJournalType.Debit
                                        : TransactionJournalType.Credit))
                    .collect(Collectors.toList()));
  }
}
