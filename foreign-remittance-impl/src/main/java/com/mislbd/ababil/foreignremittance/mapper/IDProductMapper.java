package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.IDProduct;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component("IDProductMapper")
public class IDProductMapper {
  private final IDProductRepository idProductRepository;

  public IDProductMapper(IDProductRepository idProductRepository) {
    this.idProductRepository = idProductRepository;
  }

  public ResultMapper<IDProductEntity, IDProduct> entityToDomain() {
    return entity ->
        new IDProduct()
            .setId(entity.getId())
            .setName(entity.getName())
            .setCode(entity.getCode())
            .setGeneralLedgerId(entity.getGeneralLedgerId())
            .setCurrencies(entity.getCurrencies());
  }

  public ResultMapper<IDProduct, IDProductEntity> domainToEntity() {
    return domain ->
        idProductRepository
            .findById(domain.getId())
            .orElseGet(IDProductEntity::new)
            .setId(domain.getId())
            .setName(domain.getName())
            .setCode(domain.getCode())
            .setGeneralLedgerId(domain.getGeneralLedgerId())
            .setCurrencies(domain.getCurrencies());
  }
}
