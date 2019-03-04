package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroAccount;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface NostroAccountService {
    PagedResult<NostroAccount> getAccounts(Pageable pageable);
    Optional<NostroAccount> getIdAccount(Long id);
    boolean isExists(Long id);
    NostroAccount findByNumber(String accNumber);
    public BigDecimal getBalanceCcy(String accNum);
    BigDecimal getBalanceLimits(String accNum);
}
