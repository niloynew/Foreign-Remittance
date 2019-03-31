package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CBFundSource;
import com.mislbd.ababil.foreignremittance.mapper.CBFundSourceMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.CBFundSourceRepository;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CBFundSourceServiceImpl implements CBFundSourceService {

  private final CBFundSourceRepository cbFundSourceRepository;
  private final CBFundSourceMapper cbFundSourceMapper;

  public CBFundSourceServiceImpl(
      CBFundSourceRepository cbFundSourceRepository, CBFundSourceMapper cbFundSourceMapper) {
    this.cbFundSourceRepository = cbFundSourceRepository;
    this.cbFundSourceMapper = cbFundSourceMapper;
  }

  @Override
  public PagedResult<CBFundSource> getFundSources(Pageable pageable) {
    return PagedResultBuilder.build(
        cbFundSourceRepository.findAll(pageable), cbFundSourceMapper.entityToDomain());
  }

  @Override
  public List<CBFundSource> getFundSources() {
    return ListResultBuilder.build(
        cbFundSourceRepository.findAll(), cbFundSourceMapper.entityToDomain());
  }
}
