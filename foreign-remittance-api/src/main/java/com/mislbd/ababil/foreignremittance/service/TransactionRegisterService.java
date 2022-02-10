package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.TransactionRegister;
import java.util.List;

public interface TransactionRegisterService {
  void doRegister(List<CbsTemplateTransaction> transactions, Long remittanceTransactionId);

  List<TransactionRegister> findTransactionRegisterByGlobalTxnNumber(Long globalTxnNumber);

  void invalidRegister(Long globalTxnNumber);

  List<TransactionRegister> findTransactionRegisterByRemittanceTransaction(
      Long remittanceTransactionId);
}
