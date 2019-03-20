package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface SwiftMsgService {
  PagedResult<RemittanceMsgDto> findAll(
      Pageable pageable, String msgType, String lcNo, BigDecimal amount, LocalDate valueDate);

  Optional<RemittanceMsgDto> findByID(Long id);

  RemittanceMsgDto findByLCNo(String lcNo);
  int process(List<Long> msgIds);

}
