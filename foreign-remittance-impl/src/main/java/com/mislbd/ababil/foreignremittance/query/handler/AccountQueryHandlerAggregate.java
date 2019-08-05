package com.mislbd.ababil.foreignremittance.query.handler;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.query.AccountQuery;
import com.mislbd.ababil.foreignremittance.service.AccountService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class AccountQueryHandlerAggregate {

  private AccountService accountService;

  public AccountQueryHandlerAggregate(AccountService accountService) {
    this.accountService = accountService;
  }

  @QueryHandler
  public QueryResult<?> accountSearch(AccountQuery accountQuery) {
    if (accountQuery.isAsPage()) {
      PagedResult<Account> accountPage =
          accountService.getAccounts(
              accountQuery.getPageable(),
              accountQuery.getNumber(),
              accountQuery.getName(),
              accountQuery.getNostroAccountNumber(),
              accountQuery.getBank(),
              accountQuery.getBranch(),
              accountQuery.getAccountOpenDate(),
              accountQuery.getCurrency(),
              accountQuery.getProduct());

      return QueryResult.of(accountPage);

    } else {
      List<Account> accounts =
          accountService.getAccounts(
              accountQuery.getNumber(),
              accountQuery.getName(),
              accountQuery.getNostroAccountNumber(),
              accountQuery.getBank(),
              accountQuery.getBranch(),
              accountQuery.getAccountOpenDate(),
              accountQuery.getCurrency(),
              accountQuery.getProduct());
      return QueryResult.of(accounts);
    }
  }
}
