package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionOperation;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TransactionOperationService {

  PagedResult<TransactionOperation> getOperations(
      Pageable pageable, long typeId, RemittanceType remittanceType);

  List<TransactionOperation> getOperations(long typeId, RemittanceType remittanceType);
}
