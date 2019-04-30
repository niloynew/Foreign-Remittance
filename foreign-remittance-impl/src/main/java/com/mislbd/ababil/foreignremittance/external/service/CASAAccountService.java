package com.mislbd.ababil.foreignremittance.external.service;

import com.mislbd.ababil.foreignremittance.external.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    public Account getAccountByNumber(String accountNumber) {
        try {
            Optional<Account> account = accountClient.getAccount(accountNumber);
            if (account.isPresent()) {
                return account.get();
            } else throw new DisbursementException("Account not found");
        } catch (Exception e) {
            throw new DisbursementException("Account not found");
        }
    }
}
