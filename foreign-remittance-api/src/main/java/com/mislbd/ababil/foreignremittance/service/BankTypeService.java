package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface BankTypeService {

  PagedResult<BankType> getBankTypes(Pageable pageable);

  List<BankType> getBankTypes();

  Optional<BankType> getBankType(long bankTypeId);

  BankType getMandatoryBankByRemittanceType(RemittanceType type);
}
