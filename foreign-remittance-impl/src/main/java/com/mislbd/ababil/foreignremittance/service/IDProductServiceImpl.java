package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.mapper.IDProductMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.IDProductSpecification;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("IDProductServiceImplementation")
public class IDProductServiceImpl implements IDProductService {

  private final IDProductRepository productRepository;
  private final IDProductMapper productMapper;

  public IDProductServiceImpl(
      IDProductRepository productRepository, IDProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public PagedResult<IDProduct> findIDProducts(
      Pageable pageable, String name, String code, String currency) {
    Page<IDProductEntity> pagedIDProductEntities =
        productRepository.findAll(
            IDProductSpecification.findIDProduct(name, code, currency), pageable);

    return PagedResultBuilder.build(pagedIDProductEntities, productMapper.entityToDomain());
  }

  @Override
  public Optional<IDProduct> findIDProduct(Long id) {
    return productRepository.findById(id).map(productMapper.entityToDomain()::map);
  }

  @Override
  public boolean isExists(Long id) {
    return productRepository.existsById(id);
  }
}
