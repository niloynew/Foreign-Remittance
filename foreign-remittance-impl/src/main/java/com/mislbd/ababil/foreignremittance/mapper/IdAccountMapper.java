package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.Account;
import com.mislbd.ababil.foreignremittance.repository.jpa.IdAccountRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IdAccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class IdAccountMapper {
    private final IdAccountRepository idAccountRepository;

    public IdAccountMapper(IdAccountRepository idAccountRepository) {
        this.idAccountRepository = idAccountRepository;
    }

    public ResultMapper<IdAccountEntity, Account> entityToDomain(){
        return entity -> new Account()
                .setId(entity.getId())
                .setProductId(entity.getProductId())
                .setNumber(entity.getNumber())
                .setCurrencyCode(entity.getCurrencyCode())
                .setCorrespondenceBranchId(entity.getCorrespondenceBranchId())
                .setAddressLine1(entity.getAddressLine1())
                .setAddressLine2(entity.getAddressLine2())
                .setAddressLine3(entity.getAddressLine3())
                .setPostBox(entity.getPostBox())
                .setCountryId(entity.getCountryId())
                .setCity(entity.getCity())
                .setClearingHouse(entity.getClearingHouse())
                .setSwiftCode(entity.getSwiftCode())
                .setOpenDate(entity.getOpenDate())
                .setEmail(entity.getEmail())
                .setUrl(entity.getUrl())
                .setRoutingNo(entity.getRoutingNo())
                .setTelephoneNo(entity.getTelephoneNo())
                .setMobileNo(entity.getMobileNo())
                .setFaxNo(entity.getFaxNo())
                .setContactPerson(entity.getContactPerson())
                .setMaintenanceFee(entity.getMaintenanceFee())
                .setPaymentFee(entity.getPaymentFee())
                .setStatementFee(entity.getStatementFee())
                .setMinimumBalance(entity.getMinimumBalance())
                .setBusinessDays(entity.getBusinessDays())
                .setOperatingHours(entity.getOperatingHours())
                .setBalanceLimits(entity.getBalanceLimits())
                .setAdvanceWarning(entity.getAdvanceWarning())
                .setRecStatus(entity.getRecStatus())
                .setBalanceCcy(entity.getBalanceCcy())
                .setBalanceLcy(entity.getBalanceLcy())
                .setLastOpDate(entity.getLastOpDate())
                .setBlockAmount(entity.getBlockAmount())
                .setStatus(entity.getStatus())
                .setClearingAmount(entity.getClearingAmount())
                .setNostroAcc(entity.getNostroAcc())
                .setIdAccNm(entity.getIdAccNm())
                .setBrId(entity.getBrId())
                .setAccBrId(entity.getAccBrId())
                .setNostroAccId(entity.getNostroAccId());
    }

    public ResultMapper<Account, IdAccountEntity> domainToEntity(){
        return domain ->
                idAccountRepository
                        .findById(domain.getId())
                        .orElseGet(IdAccountEntity::new)
                        .setId(domain.getId())
                        .setProductId(domain.getProductId())
                        .setNumber(domain.getNumber())
                        .setCurrencyCode(domain.getCurrencyCode())
                        .setCorrespondenceBranchId(domain.getCorrespondenceBranchId())
                        .setAddressLine1(domain.getAddressLine1())
                        .setAddressLine2(domain.getAddressLine2())
                        .setAddressLine3(domain.getAddressLine3())
                        .setPostBox(domain.getPostBox())
                        .setCountryId(domain.getCountryId())
                        .setCity(domain.getCity())
                        .setClearingHouse(domain.getClearingHouse())
                        .setSwiftCode(domain.getSwiftCode())
                        .setOpenDate(domain.getOpenDate())
                        .setEmail(domain.getEmail())
                        .setUrl(domain.getUrl())
                        .setRoutingNo(domain.getRoutingNo())
                        .setTelephoneNo(domain.getTelephoneNo())
                        .setMobileNo(domain.getMobileNo())
                        .setFaxNo(domain.getFaxNo())
                        .setContactPerson(domain.getContactPerson())
                        .setMaintenanceFee(domain.getMaintenanceFee())
                        .setPaymentFee(domain.getPaymentFee())
                        .setStatementFee(domain.getStatementFee())
                        .setMinimumBalance(domain.getMinimumBalance())
                        .setBusinessDays(domain.getBusinessDays())
                        .setOperatingHours(domain.getOperatingHours())
                        .setBalanceLimits(domain.getBalanceLimits())
                        .setAdvanceWarning(domain.getAdvanceWarning())
                        .setRecStatus(domain.getRecStatus())
                        .setBalanceCcy(domain.getBalanceCcy())
                        .setBalanceLcy(domain.getBalanceLcy())
                        .setLastOpDate(domain.getLastOpDate())
                        .setBlockAmount(domain.getBlockAmount())
                        .setStatus(domain.getStatus())
                        .setClearingAmount(domain.getClearingAmount())
                        .setNostroAcc(domain.getNostroAcc())
                        .setIdAccNm(domain.getIdAccNm())
                        .setBrId(domain.getBrId())
                        .setAccBrId(domain.getAccBrId())
                        .setNostroAccId(domain.getNostroAccId());
    }
}
