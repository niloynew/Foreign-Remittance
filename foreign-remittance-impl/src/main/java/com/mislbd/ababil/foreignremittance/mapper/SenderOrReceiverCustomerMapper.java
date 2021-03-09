package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.contacts.domain.ContactInformation;
import com.mislbd.ababil.contacts.repository.schema.ContactInformationEntity;
import com.mislbd.ababil.foreignremittance.domain.SenderOrReceiverCustomer;
import com.mislbd.ababil.foreignremittance.repository.jpa.SenderOrReceiverCustomerRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.SenderOrReceiverCustomerEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class SenderOrReceiverCustomerMapper {

  private final SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository;

  public SenderOrReceiverCustomerMapper(
      SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository) {
    this.senderOrReceiverCustomerRepository = senderOrReceiverCustomerRepository;
  }

  public ResultMapper<SenderOrReceiverCustomer, SenderOrReceiverCustomerEntity> domainToEntity() {
    return domain ->
        senderOrReceiverCustomerRepository
            .findById(domain.getId())
            .orElseGet(SenderOrReceiverCustomerEntity::new)
            .setId(domain.getId())
            .setName(domain.getName())
            .setShortName(domain.getShortName())
            .setOwnerName(domain.getOwnerName())
            .setDesignation(domain.getDesignation())
            //            .setAddressEntity(addressDomainToEntity().map(domain.getAddress()))
            .setStreet(domain.getStreet())
            .setBuildingIdentifier(domain.getBuildingIdentifier())
            .setSuitIdentifier(domain.getSuitIdentifier())
            .setFloorIdentifier(domain.getFloorIdentifier())
            .setDistrictName(domain.getDistrictName())
            .setPoBoxNumber(domain.getPoBoxNumber())
            .setPostCode(domain.getPostCode())
            .setCity(domain.getCity())
            .setState(domain.getState())
            .setCountry(domain.getCountry())
            .setContactInformationEntity(
                contactInformationDomainToEntity().map(domain.getContactInformation()))
            .setBlackListed(domain.getBlackListed())
            .setCpName(domain.getCpName())
            .setCpPhone(domain.getCpPhone())
            .setCpDesignation(domain.getCpDesignation())
            .setCpEmail(domain.getCpEmail())
            .setBlackListed(false);
  }

  public ResultMapper<SenderOrReceiverCustomerEntity, SenderOrReceiverCustomer> entityToDomain() {
    return entity ->
        new SenderOrReceiverCustomer()
            .setId(entity.getId())
            .setName(entity.getName())
            .setShortName(entity.getShortName())
            .setOwnerName(entity.getOwnerName())
            .setDesignation(entity.getDesignation())
            //            .setAddress(addressEntityToDomain().map(entity.getAddressEntity()))
            .setStreet(entity.getStreet())
            .setBuildingIdentifier(entity.getBuildingIdentifier())
            .setSuitIdentifier(entity.getSuitIdentifier())
            .setFloorIdentifier(entity.getFloorIdentifier())
            .setDistrictName(entity.getDistrictName())
            .setPoBoxNumber(entity.getPoBoxNumber())
            .setPostCode(entity.getPostCode())
            .setCity(entity.getCity())
            .setState(entity.getState())
            .setCountry(entity.getCountry())
            .setContactInformation(
                entity.getContactInformationEntity() != null
                    ? contactInformationEntityToDomain().map(entity.getContactInformationEntity())
                    : null)
            .setCpName(entity.getCpName())
            .setCpEmail(entity.getCpEmail());
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
