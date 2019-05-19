package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CBFundSource;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CBFundSourceService {

  PagedResult<CBFundSource> getFundSources(Pageable pageable, Long id);

  List<CBFundSource> getFundSources(Long id);
}
