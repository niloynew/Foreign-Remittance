package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.InwardTxnsByReferenceNumberQuery;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import com.mislbd.security.core.NgSession;

@QueryAggregate
public class InwardTxnsByReferenceNumberQueryHandlerAggregate {
  private final RemittanceTransactionService remittanceTransactionService;
  private final NgSession ngSession;

  public InwardTxnsByReferenceNumberQueryHandlerAggregate(
      RemittanceTransactionService remittanceTransactionService, NgSession ngSession) {
    this.remittanceTransactionService = remittanceTransactionService;
    this.ngSession = ngSession;
  }

  @QueryHandler
  public QueryResult<Boolean> inwardRemitTransactionSearchByReferenceNumber(
      InwardTxnsByReferenceNumberQuery inwardTxnsByReferenceNumberQuery) {
    return QueryResult.of(
        remittanceTransactionService.isExistInwardRemittanceByReferenceNumber(
            inwardTxnsByReferenceNumberQuery.getReferenceNumber()));
  }
}
