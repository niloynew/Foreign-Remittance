package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecord;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface NostroTransactionRecordService {
  PagedResult<NostroTransactionRecord> findNostroTxnRecords(Pageable pageable);

  List<NostroTransactionRecord> findByLcNo(String lcNo);

  Optional<NostroTransactionRecord> findByID(long id);
}
