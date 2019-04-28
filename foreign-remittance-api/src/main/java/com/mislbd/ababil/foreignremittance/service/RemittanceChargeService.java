package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ChargeAccountType;
import com.mislbd.ababil.foreignremittance.domain.RemittanceCharge;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface RemittanceChargeService {

  PagedResult<RemittanceCharge> getCharges(
      Pageable pageable,
      String chargeName,
      ChargeAccountType chargeAccountType,
      ChargeAccountType vatAccountType,
      Boolean status);

  List<RemittanceCharge> getCharges(
      String chargeName,
      ChargeAccountType chargeAccountType,
      ChargeAccountType vatAccountType,
      Boolean status);

  Optional<RemittanceCharge> findRemittanceChargeById(long id);
}
