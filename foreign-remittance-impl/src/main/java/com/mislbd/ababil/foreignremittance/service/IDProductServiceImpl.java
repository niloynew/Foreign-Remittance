package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.mapper.IDProductMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.IDProductSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("IDProductServiceImplementation")
public class IDProductServiceImpl implements IDProductService {

  private final IDProductRepository idProductRepository;
  private final IDProductMapper productMapper;

  public IDProductServiceImpl(
      IDProductRepository idProductRepository, IDProductMapper productMapper) {
    this.idProductRepository = idProductRepository;
    this.productMapper = productMapper;
  }

  @Override
  public PagedResult<IDProduct> findIDProducts(
      Pageable pageable, String name, String code, String currency) {
    return PagedResultBuilder.build(
        idProductRepository.findAll(
            IDProductSpecification.findIDProduct(name, code, currency), pageable),
        productMapper.entityToDomain());
  }

  @Override
  public List<IDProduct> findIDProducts(String name, String code, String currency) {
    return ListResultBuilder.build(
        idProductRepository.findAll(IDProductSpecification.findIDProduct(name, code, currency)),
        productMapper.entityToDomain());
  }

  @Override
  public Optional<IDProduct> findIDProduct(Long id) {
    return idProductRepository.findById(id).map(productMapper.entityToDomain()::map);
  }

  @Override
  public boolean isExists(Long id) {
    return idProductRepository.existsById(id);
  }
}
