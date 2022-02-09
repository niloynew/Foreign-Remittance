package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.TransactionRegister;
import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import java.util.List;

public interface TransactionRegisterService {
  void doRegister(List<CbsTransaction> transactions, Long remittanceTransactionId);

  List<TransactionRegister> findTransactionRegisterByGlobalTxnNumber(Long globalTxnNumber);

  void invalidRegister(Long globalTxnNumber);

  List<TransactionRegister> findTransactionRegisterByRemittanceTransaction(
      Long remittanceTransactionId);
}
