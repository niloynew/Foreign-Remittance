package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RateType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface RateTypeService {

  PagedResult<RateType> getClientRateTypes(Pageable pageable);

  List<RateType> getClientRateTypes();
}
