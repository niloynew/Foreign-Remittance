package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RateType;
import com.mislbd.ababil.foreignremittance.mapper.RateTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ClientRateTypeRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RateTypeServiceImpl implements RateTypeService {

  private final ClientRateTypeRepository clientRateTypeRepository;
  private final RateTypeMapper clientRateTypeMapper;

  public RateTypeServiceImpl(
      ClientRateTypeRepository clientRateTypeRepository, RateTypeMapper clientRateTypeMapper) {
    this.clientRateTypeRepository = clientRateTypeRepository;
    this.clientRateTypeMapper = clientRateTypeMapper;
  }

  @Override
  public PagedResult<RateType> getClientRateTypes(Pageable pageable) {
    return PagedResultBuilder.build(
        clientRateTypeRepository.findAll(pageable), clientRateTypeMapper.entityToDomain());
  }

  @Override
  public List<RateType> getClientRateTypes() {
    return ListResultBuilder.build(
        clientRateTypeRepository.findAll(), clientRateTypeMapper.entityToDomain());
  }
}
