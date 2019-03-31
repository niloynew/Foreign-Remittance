package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.ababil.foreignremittance.repository.jpa.ExportLCRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ExportLCEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ExportLCMapper {

  private final ExportLCRepository exportLCRepository;

  public ExportLCMapper(ExportLCRepository exportLCRepository) {
    this.exportLCRepository = exportLCRepository;
  }

  public ResultMapper<ExportLC, ExportLCEntity> domainToEntity() {
    return domain ->
        exportLCRepository
            .findById(domain.getId())
            .orElseGet(ExportLCEntity::new)
            .setId(domain.getId())
            .setName(domain.getName())
            .setShortName(domain.getShortName())
            .setOwnerName(domain.getOwnerName())
            .setDesignation(domain.getDesignation())
            .setAddress(domain.getAddress())
            .setCity(domain.getCity())
            .setPostalCode(domain.getPostalCode())
            .setPhone(domain.getPhone())
            .setMobile(domain.getMobile())
            .setWeb(domain.getWeb())
            .setFax(domain.getFax())
            .setTelex(domain.getTelex())
            .setBlackListed(domain.getBlackListed())
            .setCountry(domain.getCountry())
            .setDivision(domain.getDivision())
            .setDistrict(domain.getDistrict())
            .setCpName(domain.getCpName())
            .setCpPhone(domain.getCpPhone())
            .setCpDesignation(domain.getCpDesignation())
            .setCpEmail(domain.getCpEmail());
  }

  public ResultMapper<ExportLCEntity, ExportLC> entityToDomain() {
    return entity ->
        new ExportLC()
            .setId(entity.getId())
            .setName(entity.getName())
            .setOwnerName(entity.getOwnerName())
            .setAddress(entity.getAddress())
            .setCpName(entity.getCpName())
            .setCpEmail(entity.getCpEmail());
  }
}
