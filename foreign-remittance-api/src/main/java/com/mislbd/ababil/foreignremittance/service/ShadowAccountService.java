package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ShadowAccountService {

  PagedResult<Account> findActiveAccounts(
      Pageable pageable,
      String shadowAccountNumber,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      String accountopenDate,
      String currency,
      String product);

  List<Account> findActiveAccounts(
      String shadowAccountNumber,
      String name,
      String nostroAccountNumber,
      String bank,
      String branch,
      String accountopenDate,
      String currency,
      String product);
}
