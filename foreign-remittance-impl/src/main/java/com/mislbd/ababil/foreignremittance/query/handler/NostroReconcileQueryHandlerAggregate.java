package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.NostroReconcileQuery;
import com.mislbd.ababil.foreignremittance.query.UnreconciledTransactionQuery;
import com.mislbd.ababil.foreignremittance.service.AccountStatementService;
import com.mislbd.ababil.foreignremittance.service.NostroTransactionService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class NostroReconcileQueryHandlerAggregate {

  private final NostroTransactionService nostroTransactionService;
  private final AccountStatementService accountStatementService;

  public NostroReconcileQueryHandlerAggregate(NostroTransactionService nostroTransactionService, AccountStatementService accountStatementService) {
    this.nostroTransactionService = nostroTransactionService;
    this.accountStatementService = accountStatementService;
  }

  @QueryHandler
  public QueryResult<?> nostroReconcileSearch(NostroReconcileQuery nostroReconcileQuery) {
    if (nostroReconcileQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          nostroTransactionService.getMessages(
              nostroReconcileQuery.getPageable(),
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          nostroTransactionService.getMessages(
              nostroReconcileQuery.getId(),
              nostroReconcileQuery.getAccountNo(),
              nostroReconcileQuery.getAdvBranch(),
              nostroReconcileQuery.isSelected(),
              nostroReconcileQuery.getValueDate());
      return QueryResult.of(listResult);
    }
  }

  @QueryHandler
  public QueryResult<?> unreconciledTransactionSearch(UnreconciledTransactionQuery transactionQuery) {
    PagedResult<?> pagedResult =
            accountStatementService.getUnreconciledTransactionData(transactionQuery.getPageable(),
                    transactionQuery.getAccountNumber(),
                    transactionQuery.getFromDate(),
                    transactionQuery.getToDate(),
                    transactionQuery.getStatus());
    return QueryResult.of(pagedResult);
  }
}
