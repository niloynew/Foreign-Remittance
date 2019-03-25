package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroTransactionRecord;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NostroTransactionRecordServiceImpl implements NostroTransactionRecordService {

  @Override
  public PagedResult<NostroTransactionRecord> findNostroTxnRecords(Pageable pageable) {

    return null;
  }

  @Override
  public List<NostroTransactionRecord> findByLcNo(String lcNo) {
    return null;
  }

  @Override
  public Optional<NostroTransactionRecord> findByID(long id) {
    return Optional.empty();
  }
}
