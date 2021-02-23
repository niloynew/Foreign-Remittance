package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
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
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return shadowAccountService.findActiveAccounts(
        pageable,
        number,
        name,
        nostroAccountNumber,
        bank,
        branch,
        accountOpenDate,
        currency,
        product,
        isActive);
  }

  @Override
  public List<Account> getAccounts(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive) {
    return shadowAccountService.findActiveAccounts(
        number,
        name,
        nostroAccountNumber,
        bank,
        branch,
        accountOpenDate,
        currency,
        product,
        isActive);
  }

  @Override
  public Account findById(Long id) {
    return shadowAccountService.findById(id);
  }
}
