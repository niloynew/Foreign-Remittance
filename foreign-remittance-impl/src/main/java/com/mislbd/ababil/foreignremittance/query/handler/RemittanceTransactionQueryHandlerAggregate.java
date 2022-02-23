package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.TransactionToRequestMapper;
import com.mislbd.ababil.foreignremittance.query.Mt103RequestRemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionIdQuery;
import com.mislbd.ababil.foreignremittance.query.RemittanceTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.ababil.foreignremittance.service.RemittanceTransactionService;
import com.mislbd.ababil.organization.service.BranchService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import com.mislbd.security.core.NgSession;
import java.util.List;

@QueryAggregate
public class RemittanceTransactionQueryHandlerAggregate {
  private final RemittanceTransactionService remittanceTransactionService;
  private final NgSession ngSession;
  private final BranchService branchService;
  private final BankTypeService bankTypeService;
  private final TransactionToRequestMapper transactionToRequestMapper;

  public RemittanceTransactionQueryHandlerAggregate(
      RemittanceTransactionService remittanceTransactionService,
      NgSession ngSession,
      BranchService branchService,
      BankTypeService bankTypeService,
      TransactionToRequestMapper transactionToRequestMapper) {
    this.remittanceTransactionService = remittanceTransactionService;
    this.ngSession = ngSession;
    this.branchService = branchService;
    this.bankTypeService = bankTypeService;
    this.transactionToRequestMapper = transactionToRequestMapper;
  }

  @QueryHandler
  public QueryResult<?> remittanceTransactionSearch(
      RemittanceTransactionQuery remittanceTransactionQuery) {
    if (remittanceTransactionQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          remittanceTransactionService.getTransactions(
              remittanceTransactionQuery.getPageable(),
              remittanceTransactionQuery.getStatus(),
              remittanceTransactionQuery.getRemittanceType(),
              remittanceTransactionQuery.getTransactionReferenceNumber(),
              remittanceTransactionQuery.getFromDate(),
              remittanceTransactionQuery.getToDate());
      return QueryResult.of(pagedResult);
    } else {

      List<?> listResult =
          remittanceTransactionService.getTransactions(
              remittanceTransactionQuery.getStatus(),
              remittanceTransactionQuery.getRemittanceType(),
              remittanceTransactionQuery.getTransactionReferenceNumber(),
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

    @QueryHandler
    public QueryResult<?> getMt103RequestByRemittanceTransactionId(
        Mt103RequestRemittanceTransactionIdQuery mt103RequestRemittanceTransactionIdQuery) {
      return QueryResult.of(
          transactionToRequestMapper.mapTransactionToMessageRequest(
              remittanceTransactionService
                  .findTransaction(mt103RequestRemittanceTransactionIdQuery.getId())
                  .orElseThrow(RemittanceTransactionNotFoundException::new)));
    }
}
