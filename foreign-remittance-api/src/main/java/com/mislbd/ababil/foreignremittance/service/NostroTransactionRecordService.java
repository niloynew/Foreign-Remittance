package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.swift.broker.model.raw.NostroTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface NostroTransactionRecordService {

  PagedResult<NostroTransaction> getMessages(
      Pageable pageable,
      Long id,
      String accountNo,
      String advBranch,
      boolean selected,
      LocalDate valueDate);

  List getMessages(
      Long id, String accountNo, String advBranch, boolean selected, LocalDate valueDate);

  Optional<NostroTransaction> getMessageById(long id);

  void save(NostroTransaction dto);
}
