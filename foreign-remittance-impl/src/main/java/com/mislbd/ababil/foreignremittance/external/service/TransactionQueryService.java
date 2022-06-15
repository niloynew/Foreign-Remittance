package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.external.domain.TransactionQueryResponse;
import com.mislbd.ababil.foreignremittance.external.repository.TransactionQueryClient;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionQueryService {

  private final TransactionQueryClient transactionQueryClient;

  public TransactionQueryService(TransactionQueryClient transactionQueryClient) {
    this.transactionQueryClient = transactionQueryClient;
  }

  public List<TransactionQueryResponse> getTransactionQueryResponse(List<String> voucherNumbers) {
    if (voucherNumbers != null && !voucherNumbers.isEmpty()) {
      return transactionQueryClient.getQueryResponse(voucherNumbers);
    }
    return null;
  }
}
