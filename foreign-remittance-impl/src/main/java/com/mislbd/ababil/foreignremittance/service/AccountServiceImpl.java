package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final ShadowAccountService shadowAccountService;

  public AccountServiceImpl(ShadowAccountService shadowAccountService) {
    this.shadowAccountService = shadowAccountService;
  }

  /*
   * Fetch all the active accounts from NOSTRO_ACCOUNT table
   * */
  @Override
  public PagedResult<Account> getAccounts(
      Pageable pageable,
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      String accountopenDate,
      String currency,
      String product) {
    return shadowAccountService.findActiveAccounts(
        pageable,
        number,
        name,
        nostroAccountNumber,
        bank,
        branch,
        accountopenDate,
        currency,
        product);
  }

  @Override
  public List<Account> getAccounts(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      String accountopenDate,
      String currency,
      String product) {
    return shadowAccountService.findActiveAccounts(
        number, name, nostroAccountNumber, bank, branch, accountopenDate, currency, product);
  }
}
