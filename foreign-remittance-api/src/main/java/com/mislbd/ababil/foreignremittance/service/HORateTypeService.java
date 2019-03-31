package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.HORateType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface HORateTypeService {

  PagedResult<HORateType> getClientRateTypes(Pageable pageable);

  List<HORateType> getClientRateTypes();
}
