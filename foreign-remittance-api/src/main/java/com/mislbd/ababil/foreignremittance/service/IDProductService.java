package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface IDProductService {

  PagedResult<IDProduct> findIDProducts(
      Pageable pageable, String name, String code, String currency);

  List<IDProduct> findIDProducts(String name, String code, String currency);

  Optional<IDProduct> findIDProduct(Long id);

  boolean isExists(Long id);
}
