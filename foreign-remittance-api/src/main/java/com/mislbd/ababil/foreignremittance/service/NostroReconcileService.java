package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface NostroReconcileService {

  PagedResult<NostroReconcileDtoBroker> getMessages(
      Pageable pageable,
      Long id,
      String accountNo,
      String advBranch,
      boolean selected,
      LocalDate valueDate);

  List getMessages(
      Long id, String accountNo, String advBranch, boolean selected, LocalDate valueDate);

  Optional<NostroReconcileDtoBroker> getMessageById(long id);

  void save(NostroReconcileDtoBroker dto);
}
