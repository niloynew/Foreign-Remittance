package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.transaction.api.transaction.model.Charge;
import java.math.BigDecimal;
import java.util.List;

public interface RemittanceChargeInformationService {

  List<Charge> getChargeInfo(
      RemittanceType remittanceType, long transactionTypeId, BigDecimal amount);
}
