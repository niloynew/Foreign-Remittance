package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceMsgDtoMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftMsgRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.SwiftMsgSpecification;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SwiftMsgServiceImpl implements SwiftMsgService {

  private final SwiftMsgRepository swiftMsgRepository;
  private final RemittanceMsgDtoMapper remittanceMsgDtoMapper;

  public SwiftMsgServiceImpl(
      SwiftMsgRepository swiftMsgRepository, RemittanceMsgDtoMapper remittanceMsgDtoMapper) {
    this.swiftMsgRepository = swiftMsgRepository;
    this.remittanceMsgDtoMapper = remittanceMsgDtoMapper;
  }

  @Override
  public PagedResult<RemittanceMsgDto> findAll(
      Pageable pageable, String msgType, String lcNo, BigDecimal amount, LocalDate valueDate) {

    return PagedResultBuilder.build(
        swiftMsgRepository.findAll(
            SwiftMsgSpecification.searchSpecification(msgType, lcNo, amount, valueDate), pageable),
        remittanceMsgDtoMapper.entityToDomain());
  }

  @Override
  public Optional<RemittanceMsgDto> findByID(Long id) {
    return Optional.empty();
  }

  @Override
  public RemittanceMsgDto findByLCNo(String lcNo) {
    return null;
  }

  @Override
  public int process(List<Long> msgIds) {

    return 0;
  }
}
