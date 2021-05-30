package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AccountService {

  PagedResult<Account> getAccounts(
      Pageable pageable,
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive);

  List<Account> getAccounts(
      String number,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      LocalDate accountOpenDate,
      String currency,
      String product,
      Boolean isActive);

  Account findById(Long id);

  List<Account> getAccountsByBICCode(String bicCode);
}
