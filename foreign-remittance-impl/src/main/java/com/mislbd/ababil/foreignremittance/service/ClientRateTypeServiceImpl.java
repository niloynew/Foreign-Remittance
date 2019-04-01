package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ClientRateType;
import com.mislbd.ababil.foreignremittance.mapper.ClientRateTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ClientRateTypeRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientRateTypeServiceImpl implements ClientRateTypeService {

  private final ClientRateTypeRepository clientRateTypeRepository;
  private final ClientRateTypeMapper clientRateTypeMapper;

  public ClientRateTypeServiceImpl(
      ClientRateTypeRepository clientRateTypeRepository,
      ClientRateTypeMapper clientRateTypeMapper) {
    this.clientRateTypeRepository = clientRateTypeRepository;
    this.clientRateTypeMapper = clientRateTypeMapper;
  }

  @Override
  public PagedResult<ClientRateType> getClientRateTypes(Pageable pageable) {
    return PagedResultBuilder.build(
        clientRateTypeRepository.findAll(pageable), clientRateTypeMapper.entityToDomain());
  }

  @Override
  public List<ClientRateType> getClientRateTypes() {
    return ListResultBuilder.build(
        clientRateTypeRepository.findAll(), clientRateTypeMapper.entityToDomain());
  }
}
