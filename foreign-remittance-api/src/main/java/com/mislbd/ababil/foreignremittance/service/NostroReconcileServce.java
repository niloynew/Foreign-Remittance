package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface NostroReconcileServce {

  PagedResult<NostroReconcileDto> getMessages(
      Pageable pageable, String accountNo, LocalDate valueDate);

  Optional<NostroReconcileDto> getMessageById(long id);

  void save(NostroReconcileDto dto);
}
