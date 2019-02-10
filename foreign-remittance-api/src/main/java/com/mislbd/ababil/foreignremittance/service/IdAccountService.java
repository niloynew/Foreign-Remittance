package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.IdAccount;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface IdAccountService {
    PagedResult<IdAccount> getAccounts(Pageable pageable);
    Optional<IdAccount> getIdAccount(Long id);
    boolean isExists(Long id);
    IdAccount findByNumber(String accNumber);
    public BigDecimal getBalanceCcy(String accNum);
    BigDecimal getBalanceLcy(String accNum);

}
