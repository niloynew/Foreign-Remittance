package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.TransactionOperation;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TransactionOperationService {

  PagedResult<TransactionOperation> getOperations(Pageable pageable, long typeId);

  List<TransactionOperation> getOperations(long typeId);
}
