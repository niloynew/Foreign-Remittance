package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import java.math.BigDecimal;
import java.util.List;

public interface RemittanceChargeInformationService {

  List<RemittanceChargeInformation> getChargeInfo(
      RemittanceType remittanceType,
      long transactionTypeId,
      String accountNumber,
      BigDecimal amount);
}
