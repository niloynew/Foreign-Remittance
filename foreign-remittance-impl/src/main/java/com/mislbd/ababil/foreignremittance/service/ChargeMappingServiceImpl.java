package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceChargeMappingMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceChargeMappingRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.RemittanceChargeMappingSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChargeMappingServiceImpl implements ChargeMappingService {

  private final RemittanceChargeMappingRepository remittanceChargeMappingRepository;
  private final RemittanceChargeMappingMapper remittanceChargeMappingMapper;

  public ChargeMappingServiceImpl(
      RemittanceChargeMappingRepository remittanceChargeMappingRepository,
      RemittanceChargeMappingMapper remittanceChargeMappingMapper) {
    this.remittanceChargeMappingRepository = remittanceChargeMappingRepository;
    this.remittanceChargeMappingMapper = remittanceChargeMappingMapper;
  }

  @Override
  public PagedResult<RemittanceChargeMapping> findAll(
      Pageable pageable,
      RemittanceType remittanceType,
      Long typeId,
      Long remittanceChargeId,
      Boolean chargeModifiable,
      Boolean vatModifiable) {
    return PagedResultBuilder.build(
        remittanceChargeMappingRepository.findAll(
            RemittanceChargeMappingSpecification.findSpecificChargeMappings(
                remittanceType, typeId, remittanceChargeId, chargeModifiable, vatModifiable),
            pageable),
        remittanceChargeMappingMapper.entityToDomain());
  }

  @Override
  public List<RemittanceChargeMapping> findAll(
      RemittanceType remittanceType,
      Long typeId,
      Long remittanceChargeId,
      Boolean chargeModifiable,
      Boolean vatModifiable) {
    return ListResultBuilder.build(
        remittanceChargeMappingRepository.findAll(
            RemittanceChargeMappingSpecification.findSpecificChargeMappings(
                remittanceType, typeId, remittanceChargeId, chargeModifiable, vatModifiable)),
        remittanceChargeMappingMapper.entityToDomain());
  }
}
