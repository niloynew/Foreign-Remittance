package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionException;
import com.mislbd.ababil.foreignremittance.external.domain.Account;
import com.mislbd.ababil.foreignremittance.external.domain.Balance;
import com.mislbd.ababil.foreignremittance.external.domain.DepositAccount;
import com.mislbd.ababil.foreignremittance.external.repository.CASAAccountClient;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CASAAccountService {

  private final CASAAccountClient accountClient;

  public CASAAccountService(CASAAccountClient accountClient) {
    this.accountClient = accountClient;
  }

  public Account getAccountByNumber(String accountNumber) {
    try {
      Optional<Account> account = accountClient.getAccount(accountNumber);
      if (account.isPresent()) {
        return account.get();
      } else throw new RemittanceTransactionException("Account not found");
    } catch (Exception e) {
      throw new RemittanceTransactionException("Account not found");
    }
  }

  public Balance getDepositAccountBalance(String accountNumber) {
    PagedResult<DepositAccount> demandDepositAccount =
        accountClient.getAccountBalance(accountNumber);
    if (!demandDepositAccount.getContent().isEmpty()) {
      return demandDepositAccount.getContent().get(0).getBalance();
    } else {
      throw new RemittanceTransactionException(accountNumber + " account not found");
    }
  }
}
