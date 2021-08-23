package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.SwiftBankConfiguration;
import com.mislbd.ababil.foreignremittance.domain.TransactionType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TransactionTypeService {

  PagedResult<TransactionType> getTypes(Pageable pageable, Long id, RemittanceType remittanceType);

  List<TransactionType> getTypes(Long id, RemittanceType remittanceType);
  //List<SwiftBankConfiguration> getConfigurations (Long id);

}
