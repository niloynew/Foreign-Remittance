package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.query.BankTypeIdQuery;
import com.mislbd.ababil.foreignremittance.query.BankTypeQuery;
import com.mislbd.ababil.foreignremittance.service.BankTypeService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;
import java.util.Optional;

@QueryAggregate
public class BankTypeQueryHandlerAggregate {

  private BankTypeService bankTypeService;

  public BankTypeQueryHandlerAggregate(BankTypeService bankTypeService) {
    this.bankTypeService = bankTypeService;
  }

  @QueryHandler
  public QueryResult<?> bankTypeSearch(BankTypeQuery bankTypeQuery) {
    if (bankTypeQuery.isAsPage()) {
      PagedResult<BankType> accountTypePage =
          bankTypeService.getBankTypes(bankTypeQuery.getPageable());

      return QueryResult.of(accountTypePage);

    } else {
      List<BankType> BankTypes = bankTypeService.getBankTypes();
      return QueryResult.of(BankTypes);
    }
  }

  @QueryHandler
  public QueryResult<?> bankTypeIdSearch(BankTypeIdQuery bankTypeIdQuery) {

    Optional BankType = bankTypeService.getBankType(bankTypeIdQuery.getId());

    return QueryResult.of(BankType);
  }
}
