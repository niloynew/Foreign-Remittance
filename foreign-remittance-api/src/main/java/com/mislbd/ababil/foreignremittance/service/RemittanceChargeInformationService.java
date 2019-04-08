package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeInformation;
import java.math.BigDecimal;
import java.util.List;

public interface RemittanceChargeInformationService {

  List<RemittanceChargeInformation> getChargeInfo(
      long transactionTypeId, String accountNumber, BigDecimal amount);
}
