package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.NostroAccount;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroAccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class NostroAccountMapper {
  private final NostroAccountRepository nostroAccountRepository;

  public NostroAccountMapper(NostroAccountRepository nostroAccountRepository) {
    this.nostroAccountRepository = nostroAccountRepository;
  }

  public ResultMapper<NostroAccountEntity, NostroAccount> entityToDomain() {
    return entity ->
        new NostroAccount()
            .setNostroAccId(entity.getNostroAccId())
            .setNumber(entity.getNumber())
            .setName(entity.getName())
            .setCurrencyCode(entity.getCurrencyCode())
            .setCorrespondenceBrId(entity.getCorrespondenceBrId())
            .setAddressLine1(entity.getAddressLine1())
            .setAddressLine2(entity.getAddressLine2())
            .setAddressLine3(entity.getAddressLine3())
            .setNostroPostBox(entity.getNostroPostBox())
            .setCountryId(entity.getCountryId())
            .setCity(entity.getCity())
            .setClearHouse(entity.getClearHouse())
            .setSwiftCode(entity.getSwiftCode())
            .setAccOpenDate(entity.getAccOpenDate())
            .setEmail(entity.getEmail())
            .setUrl(entity.getUrl())
            .setRoutingUidNo(entity.getRoutingUidNo())
            .setTelephoneNo(entity.getTelephoneNo())
            .setMobileNo(entity.getMobileNo())
            .setFaxNo(entity.getFaxNo())
            .setContactPerson(entity.getContactPerson())
            .setMaintenanceFee(entity.getMaintenanceFee())
            .setOperatingHours(entity.getOperatingHours())
            .setNostroBalanceLimits(entity.getNostroBalanceLimits())
            .setAdvanceWarning(entity.getAdvanceWarning())
            .setRecStatus(entity.getRecStatus())
            .setBalanceCcy(entity.getBalanceCcy())
            .setLastOpDate(entity.getLastOpDate())
            .setBlockAmount(entity.getBlockAmount())
            .setStatus(entity.getStatus())
            .setClearingAmount(entity.getClearingAmount())
            .setBrId(entity.getBrId());
  }

  public ResultMapper<NostroAccount, NostroAccountEntity> domainToEntity() {
    return domain ->
        nostroAccountRepository
            .findById(domain.getNostroAccId())
            .orElseGet(NostroAccountEntity::new)
            .setNostroAccId(domain.getNostroAccId())
            .setNumber(domain.getNumber())
            .setName(domain.getName())
            .setCurrencyCode(domain.getCurrencyCode())
            .setCorrespondenceBrId(domain.getCorrespondenceBrId())
            .setAddressLine1(domain.getAddressLine1())
            .setAddressLine2(domain.getAddressLine2())
            .setAddressLine3(domain.getAddressLine3())
            .setNostroPostBox(domain.getNostroPostBox())
            .setCountryId(domain.getCountryId())
            .setCity(domain.getCity())
            .setClearHouse(domain.getClearHouse())
            .setSwiftCode(domain.getSwiftCode())
            .setAccOpenDate(domain.getAccOpenDate())
            .setEmail(domain.getEmail())
            .setUrl(domain.getUrl())
            .setRoutingUidNo(domain.getRoutingUidNo())
            .setTelephoneNo(domain.getTelephoneNo())
            .setMobileNo(domain.getMobileNo())
            .setFaxNo(domain.getFaxNo())
            .setContactPerson(domain.getContactPerson())
            .setMaintenanceFee(domain.getMaintenanceFee())
            .setOperatingHours(domain.getOperatingHours())
            .setNostroBalanceLimits(domain.getNostroBalanceLimits())
            .setAdvanceWarning(domain.getAdvanceWarning())
            .setRecStatus(domain.getRecStatus())
            .setBalanceCcy(domain.getBalanceCcy())
            .setLastOpDate(domain.getLastOpDate())
            .setBlockAmount(domain.getBlockAmount())
            .setStatus(domain.getStatus())
            .setClearingAmount(domain.getClearingAmount())
            .setBrId(domain.getBrId());
  }
}
