package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.ababil.foreignremittance.mapper.ExportLCMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ExportLCRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.ExportLCSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExportLCServiceImpl implements ExportLCService {

  private final ExportLCMapper exportLCMapper;
  private final ExportLCRepository exportLCRepository;

  public ExportLCServiceImpl(ExportLCMapper exportLCMapper, ExportLCRepository exportLCRepository) {
    this.exportLCMapper = exportLCMapper;
    this.exportLCRepository = exportLCRepository;
  }

  @Override
  public PagedResult<ExportLC> getLcs(
      Pageable pageable,
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail) {
    return PagedResultBuilder.build(
        exportLCRepository.findAll(
            ExportLCSpecification.findSpecificLcs(
                name, ownerName, address, country, cpName, cpEmail),
            pageable),
        exportLCMapper.entityToDomain());
  }

  @Override
  public List<ExportLC> getLcList(
      String name,
      String ownerName,
      String address,
      String country,
      String cpName,
      String cpEmail) {
    return ListResultBuilder.build(
        exportLCRepository.findAll(
            ExportLCSpecification.findSpecificLcs(
                name, ownerName, address, country, cpName, cpEmail)),
        exportLCMapper.entityToDomain());
  }

  @Override
  public Optional<ExportLC> getLc(Long id) {
    return exportLCRepository.findById(id).map(exportLCMapper.entityToDomain()::map);
  }
}
