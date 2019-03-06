package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShadowAccountService {

    PagedResult<Account> findActiveAccounts(Pageable pageable);
    List<Account> findActiveAccounts();
}
