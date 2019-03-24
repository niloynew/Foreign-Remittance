package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceChargeSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RemittanceChargeServiceImpl implements RemittanceChargeService {

  private final RemittanceChargeRepository remittanceChargeRepository;
  private final RemittanceChargeMapper remittanceChargeMapper;

  public RemittanceChargeServiceImpl(
      RemittanceChargeRepository remittanceChargeRepository,
      RemittanceChargeMapper remittanceChargeMapper) {
    this.remittanceChargeRepository = remittanceChargeRepository;
    this.remittanceChargeMapper = remittanceChargeMapper;
  }

  @Override
  public PagedResult<RemittanceCharge> getCharges(
      Pageable pageable,
      String chargeName,
      ChargeAccountType chargeAccountType,
      ChargeAccountType vatAccountType,
      Boolean status) {
    return PagedResultBuilder.build(
        remittanceChargeRepository.findAll(
            RemittanceChargeSpecification.findAllCharges(
                chargeName, chargeAccountType, vatAccountType, status),
            pageable),
        remittanceChargeMapper.entityToDomain());
  }

  @Override
  public List<RemittanceCharge> getCharges(
      String chargeName,
      ChargeAccountType chargeAccountType,
      ChargeAccountType vatAccountType,
      Boolean status) {
    return ListResultBuilder.build(
        remittanceChargeRepository.findAll(
            RemittanceChargeSpecification.findAllCharges(
                chargeName, chargeAccountType, vatAccountType, status)),
        remittanceChargeMapper.entityToDomain());
  }
}
