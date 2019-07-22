package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class RemittanceTransactionQueryHandlerAggregate {
  private final RemittanceTransactionService remittanceTransactionService;

  public RemittanceTransactionQueryHandlerAggregate(
      RemittanceTransactionService remittanceTransactionService) {
    this.remittanceTransactionService = remittanceTransactionService;
  }

  @QueryHandler
  public QueryResult<?> remittanceTransactionSearch(
      RemittanceTransactionQuery remittanceTransactionQuery) {
    if (remittanceTransactionQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          remittanceTransactionService.getTransactions(
              remittanceTransactionQuery.getPageable(),
              remittanceTransactionQuery.getGlobalTransactionNo(),
              remittanceTransactionQuery.getRemittanceType(),
              remittanceTransactionQuery.getTransactionReferenceNumber(),
              remittanceTransactionQuery.getApplicantName(),
              remittanceTransactionQuery.getBeneficiaryName(),
              remittanceTransactionQuery.getFromDate(),
              remittanceTransactionQuery.getToDate());
      return QueryResult.of(pagedResult);
    } else {

      List<?> listResult =
          remittanceTransactionService.getTransactions(
              remittanceTransactionQuery.getGlobalTransactionNo(),
              remittanceTransactionQuery.getRemittanceType(),
              remittanceTransactionQuery.getTransactionReferenceNumber(),
              remittanceTransactionQuery.getApplicantName(),
              remittanceTransactionQuery.getBeneficiaryName(),
              remittanceTransactionQuery.getFromDate(),
              remittanceTransactionQuery.getToDate());
      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> remittanceTransactionSearchById(
      RemittanceTransactionIdQuery remittanceTransactionIdQuery) {
    return QueryResult.of(
        remittanceTransactionService.findTransaction(remittanceTransactionIdQuery.getId()));
  }
}
