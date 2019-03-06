package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final ShadowAccountService shadowAccountService;

    public AccountServiceImpl(ShadowAccountService shadowAccountService) {
        this.shadowAccountService = shadowAccountService;
    }

    /*
    * Fetch all the active accounts from NOSTRO_ACCOUNT table
    * */
    @Override
    public PagedResult<Account> getAccounts(Pageable pageable) {
        return shadowAccountService.findActiveAccounts(pageable);
    }

    @Override
    public List<Account> getAccounts() {
        return shadowAccountService.findActiveAccounts();
    }


}
