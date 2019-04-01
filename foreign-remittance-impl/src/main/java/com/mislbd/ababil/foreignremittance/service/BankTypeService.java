package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BankTypeService {

  PagedResult<BankType> getBankTypes(Pageable pageable);

  List<BankType> getBankTypes();
}
