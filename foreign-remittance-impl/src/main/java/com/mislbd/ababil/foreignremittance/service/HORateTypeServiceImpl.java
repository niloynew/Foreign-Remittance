package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.HORateType;
import com.mislbd.ababil.foreignremittance.mapper.HORateTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.HORateTypeRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HORateTypeServiceImpl implements HORateTypeService {

  private final HORateTypeRepository hoRateTypeRepository;
  private final HORateTypeMapper hoRateTypeMapper;

  public HORateTypeServiceImpl(
      HORateTypeRepository hoRateTypeRepository, HORateTypeMapper hoRateTypeMapper) {
    this.hoRateTypeRepository = hoRateTypeRepository;
    this.hoRateTypeMapper = hoRateTypeMapper;
  }

  @Override
  public PagedResult<HORateType> getClientRateTypes(Pageable pageable) {
    return PagedResultBuilder.build(
        hoRateTypeRepository.findAll(pageable), hoRateTypeMapper.entityToDomain());
  }

  @Override
  public List<HORateType> getClientRateTypes() {
    return ListResultBuilder.build(
        hoRateTypeRepository.findAll(), hoRateTypeMapper.entityToDomain());
  }
}
