package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.contacts.domain.Address;
import com.mislbd.ababil.contacts.domain.ContactInformation;
import com.mislbd.ababil.contacts.repository.jpa.*;
import com.mislbd.ababil.contacts.repository.schema.AddressEntity;
import com.mislbd.ababil.contacts.repository.schema.ContactInformationEntity;
import com.mislbd.ababil.foreignremittance.domain.ExportLC;
import com.mislbd.ababil.foreignremittance.repository.jpa.ExportLCRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ExportLCEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ExportLCMapper {

  private final ExportLCRepository exportLCRepository;
  private final DivisionRepository divisionRepository;
  private final DistrictRepository districtRepository;
  private final UpazillaRepository upazillaRepository;
  private final PostCodeRepository postCodeRepository;
  private final CountryRepository countryRepository;

  public ExportLCMapper(
      ExportLCRepository exportLCRepository,
      DivisionRepository divisionRepository,
      DistrictRepository districtRepository,
      UpazillaRepository upazillaRepository,
      PostCodeRepository postCodeRepository,
      CountryRepository countryRepository) {
    this.exportLCRepository = exportLCRepository;
    this.divisionRepository = divisionRepository;
    this.districtRepository = districtRepository;
    this.upazillaRepository = upazillaRepository;
    this.postCodeRepository = postCodeRepository;
    this.countryRepository = countryRepository;
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
            .setAddressEntity(addressDomainToEntity().map(domain.getAddress()))
            .setContactInformationEntity(
                contactInformationDomainToEntity().map(domain.getContactInformation()))
            .setBlackListed(domain.getBlackListed())
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
            .setShortName(entity.getShortName())
            .setOwnerName(entity.getOwnerName())
            .setDesignation(entity.getDesignation())
            .setAddress(addressEntityToDomain().map(entity.getAddressEntity()))
            .setContactInformation(
                contactInformationEntityToDomain().map(entity.getContactInformationEntity()))
            .setCpName(entity.getCpName())
            .setCpEmail(entity.getCpEmail());
  }

  private ResultMapper<Address, AddressEntity> addressDomainToEntity() {
    return domain -> {
      AddressEntity entity = new AddressEntity();
      entity.setAddressLine(domain.getAddressLine());
      entity.setDivision(divisionRepository.findById(domain.getDivisionId()).get());
      entity.setDistrict(districtRepository.getOne(domain.getDistrictId()));
      entity.setUpazilla(upazillaRepository.getOne(domain.getDistrictId()));
      entity.setPostCode(postCodeRepository.getOne(domain.getPostCodeId()));
      entity.setCountry(countryRepository.getOne(domain.getCountryId()));
      return entity;
    };
  }

  private ResultMapper<AddressEntity, Address> addressEntityToDomain() {
    return entity -> {
      Address domain = new Address();
      domain.setAddressLine(entity.getAddressLine());
      domain.setDivisionId(entity.getDivision().getId());
      domain.setDistrictId(entity.getDistrict().getId());
      domain.setUpazillaId(entity.getUpazilla().getId());
      domain.setPostCodeId(entity.getPostCode().getId());
      domain.setCountryId(entity.getCountry().getId());
      return domain;
    };
  }

  private ResultMapper<ContactInformation, ContactInformationEntity>
      contactInformationDomainToEntity() {
    return domain -> {
      ContactInformationEntity entity = new ContactInformationEntity();
      entity.setPhoneNumber(domain.getPhoneNumber());
      entity.setMobileNumber(domain.getMobileNumber());
      entity.setAlternateMobileNumber(domain.getAlternateMobileNumber());
      entity.setEmail(domain.getEmail());
      entity.setFax(domain.getFax());
      return entity;
    };
  }

  private ResultMapper<ContactInformationEntity, ContactInformation>
      contactInformationEntityToDomain() {
    return entity -> {
      ContactInformation domain = new ContactInformation();
      domain.setPhoneNumber(entity.getPhoneNumber());
      domain.setAlternateMobileNumber(entity.getAlternateMobileNumber());
      domain.setEmail(entity.getEmail());
      domain.setFax(entity.getFax());
      domain.setEmail(entity.getEmail());
      return domain;
    };
  }
}
