package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.asset.commons.data.domain.PagedResult;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface SwiftMsgService {
     PagedResult<RemittanceMsgDto> findAll(Pageable pageable, String msgType, String lcNo, BigDecimal amount, LocalDate valueDate);
     Optional <RemittanceMsgDto> findByID(Long id);
     RemittanceMsgDto findByLCNo(String lcNo);

}
