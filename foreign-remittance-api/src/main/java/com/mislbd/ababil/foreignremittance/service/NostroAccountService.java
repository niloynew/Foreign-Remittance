package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;

public interface NostroAccountService {
  Account findById(Long id);
  //    PagedResult<Account> getAccounts(Pageable pageable);
  //    Optional<Account> getIdAccount(Long id);
  //    boolean isExists(Long id);
  //    Account findByNumber(String accNumber);
  //    public BigDecimal getBalanceCcy(String accNum);
  //    BigDecimal getBalanceLimits(String accNum);
}
