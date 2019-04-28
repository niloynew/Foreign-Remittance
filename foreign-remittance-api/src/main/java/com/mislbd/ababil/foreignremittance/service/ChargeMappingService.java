package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceChargeMapping;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ChargeMappingService {

  PagedResult<RemittanceChargeMapping> findAll(
      Pageable pageable, Long typeId, Long chargeId, Boolean chargeModifiable);

  List<RemittanceChargeMapping> findAll(Long typeId, Long chargeId, Boolean chargeModifiable);
}
