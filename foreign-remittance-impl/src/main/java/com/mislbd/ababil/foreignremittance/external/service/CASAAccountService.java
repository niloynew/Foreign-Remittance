package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.exception.RemittanceTransactionException;
import com.mislbd.ababil.foreignremittance.external.domain.Account;
import com.mislbd.ababil.foreignremittance.external.repository.CASAAccountClient;
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
}
