package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.TransactionRegister;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionRegisterEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionRegisterMapper {

    public ResultMapper<CbsTemplateTransaction, TransactionRegisterEntity> cbsDomainEntity(
            Long voucherNumber) {
        return txn ->
                new TransactionRegisterEntity()
                        .setSetNumber(txn.getSetNumber())
                        .setTransactionTypeCode(txn.getTransactionTypeCode())
                        .setTransactionType(txn.getTransactionType())
                        .setAccountNumber(txn.getAccountNumber())
                        .setAccountType(txn.getAccountType())
                        .setAmountCCY(txn.getAmountCCY())
                        .setAmountRCY(txn.getAmountRCY())
                        .setAmountType(txn.getAmountType())
                        .setExchangeRate(txn.getExchangeRate())
                        .setRateType(txn.getRateType())
                        .setNarration(txn.getNarration())
                        .setInstrumentNumber(txn.getInstrumentNumber())
                        .setInstrumentDate(txn.getInstrumentDate())
                        .setAccountCurrencyCode(txn.getAccountCurrencyCode())
                        .setCurrencyCode(txn.getCurrencyCode())
                        .setValueDate(txn.getValueDate())
                        .setVoucherNumber(voucherNumber)
                        .setReferenceNumber(txn.getReferenceNumber())
                        .setAccountBranchId(txn.getAccountBranchId())
                        .setInitiatorBranchId(txn.getInitiatorBranchId())
                        .setAdjReferenceId(txn.getAdjReferenceId())
                        .setIbtaRequired(txn.isIbtaRequired());
    }

    public ResultMapper<TransactionRegisterEntity, TransactionRegister> entityToDomain() {
        return entity ->
                new TransactionRegister()
                        .setSetNumber(entity.getSetNumber())
                        .setTransactionTypeCode(entity.getTransactionTypeCode())
                        .setTransactionEntryType(entity.getTransactionEntryType())
                        .setTransactionType(entity.getTransactionType())
                        .setAccountNumber(entity.getAccountNumber())
                        .setAccountType(entity.getAccountType())
                        .setAmountCCY(entity.getAmountCCY())
                        .setAmountRCY(entity.getAmountRCY())
                        .setAmountType(entity.getAmountType())
                        .setExchangeRate(entity.getExchangeRate())
                        .setRateType(entity.getRateType())
                        .setNarration(entity.getNarration())
                        .setInstrumentNumber(entity.getInstrumentNumber())
                        .setInstrumentDate(entity.getInstrumentDate())
                        .setAccountCurrencyCode(entity.getAccountCurrencyCode())
                        .setCurrencyCode(entity.getCurrencyCode())
                        .setValueDate(entity.getValueDate())
                        .setVoucherNumber(entity.getVoucherNumber())
                        .setReferenceNumber(entity.getReferenceNumber())
                        .setAccountBranchId(entity.getAccountBranchId())
                        .setInitiatorBranchId(entity.getInitiatorBranchId())
                        .setIbtaRequired(entity.isIbtaRequired())
                        .setRemittanceTransactionId(entity.getRemittanceTransactionId())
                        .setAdjReferenceId(entity.getAdjReferenceId())
                        .setValid(entity.isValid());
    }
}
