package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankTypeServiceImpl implements BankTypeService {
  @Override
  public PagedResult<BankType> getBankTypes(Pageable pageable) {
    return null;
  }

  @Override
  public List<BankType> getBankTypes() {
    return null;
  }

  @Override
  public Optional<BankType> getBankType(long bankTypeId) {
    return Optional.empty();
  }

  @Override
  public BankType getMandatoryBankByRemittanceType(RemittanceType type) {
    return null;
  }
}
