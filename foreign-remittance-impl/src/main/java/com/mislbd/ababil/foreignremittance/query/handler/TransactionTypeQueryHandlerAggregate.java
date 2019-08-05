package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.query.TransactionTypeQuery;
import com.mislbd.ababil.foreignremittance.service.TransactionTypeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class TransactionTypeQueryHandlerAggregate {
  private TransactionTypeService transactionTypeService;

  public TransactionTypeQueryHandlerAggregate(TransactionTypeService transactionTypeService) {
    this.transactionTypeService = transactionTypeService;
  }

  @QueryHandler
  public QueryResult<?> transactionTypeSearch(TransactionTypeQuery transactionTypeQuery) {
    if (transactionTypeQuery.isAsPage()) {
      PagedResult<?> pagedResult =
          transactionTypeService.getTypes(
              transactionTypeQuery.getPageable(),
              transactionTypeQuery.getId(),
              transactionTypeQuery.getRemittanceType());
      return QueryResult.of(pagedResult);
    } else {
      List<?> listResult =
          transactionTypeService.getTypes(
              transactionTypeQuery.getId(), transactionTypeQuery.getRemittanceType());
      return QueryResult.of(listResult);
    }
  }
}
