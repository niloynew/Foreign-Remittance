package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AccountService {

  PagedResult<Account> getAccounts(Pageable pageable);

  List<Account> getAccounts();
}
