package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankTypeService {

    PagedResult<BankType> getBankTypes(Pageable pageable);

    List<BankType> getBankTypes();
}
