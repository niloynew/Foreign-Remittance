package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.BankType;
import com.mislbd.ababil.foreignremittance.mapper.BankTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankTypeRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankTypeServiceImpl implements BankTypeService {

  private final BankTypeRepository bankTypeRepository;
  private final BankTypeMapper bankTypeMapper;

  public BankTypeServiceImpl(BankTypeRepository bankTypeRepository, BankTypeMapper bankTypeMapper) {
    this.bankTypeRepository = bankTypeRepository;
    this.bankTypeMapper = bankTypeMapper;
  }

  @Override
  public PagedResult<BankType> getBankTypes(Pageable pageable) {
    return PagedResultBuilder.build(
        bankTypeRepository.findAll(pageable), bankTypeMapper.entityToDomain());
  }

  @Override
  public List<BankType> getBankTypes() {
    return ListResultBuilder.build(bankTypeRepository.findAll(), bankTypeMapper.entityToDomain());
  }
}
