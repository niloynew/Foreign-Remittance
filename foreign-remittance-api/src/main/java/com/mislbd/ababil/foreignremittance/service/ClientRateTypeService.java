package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.ClientRateType;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ClientRateTypeService {

  PagedResult<ClientRateType> getClientRateTypes(Pageable pageable);

  List<ClientRateType> getClientRateTypes();
}
