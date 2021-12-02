package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMessageDto;
import com.mislbd.ababil.foreignremittance.domain.XmmRequest;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.swift.broker.model.MessageResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface SwiftMsgService {
  PagedResult<RemittanceMessageDto> findAll(
      Pageable pageable, String msgType, String lcNo, BigDecimal amount, LocalDate valueDate);

  Optional<RemittanceMessageDto> findByID(Long id);

  RemittanceMessageDto findByLCNo(String lcNo);

  int process(List<Long> msgIds);

  String getSwiftMiddlewareUrl();

  MessageResponse generateSwiftMT103(XmmRequest xmmRequest) throws IOException;
}
