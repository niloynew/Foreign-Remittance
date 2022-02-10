package com.mislbd.ababil.foreignremittance.mapper;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.transaction.api.transaction.model.CbsTransaction;
import org.springframework.stereotype.Component;

@Component
public class TemplateBaseTransactionMapper {

  public ResultMapper<CbsTransaction, CbsTemplateTransaction> cbsTxnToRemittanceTxn() {
    return x -> {
      CbsTemplateTransaction cbsTemplateTransaction = new CbsTemplateTransaction();
      cbsTemplateTransaction.setSetNumber(x.getSetNumber());
      cbsTemplateTransaction.setTransactionTypeCode(x.getTransactionTypeCode());
      cbsTemplateTransaction.setTransactionEntryType(x.getTransactionEntryType());
      cbsTemplateTransaction.setTransactionType(x.getTransactionType());
      cbsTemplateTransaction.setAccountNumber(x.getAccountNumber());
      cbsTemplateTransaction.setAccountType(x.getAccountType());
      cbsTemplateTransaction.setAmountCCY(x.getAmountCCY());
      cbsTemplateTransaction.setAmountRCY(x.getAmountRCY());
      cbsTemplateTransaction.setAmountType(x.getAmountType());
      cbsTemplateTransaction.setExchangeRate(x.getExchangeRate());
      cbsTemplateTransaction.setRateType(x.getRateType());
      cbsTemplateTransaction.setNarration(x.getNarration());
      cbsTemplateTransaction.setInstrumentNumber(x.getInstrumentNumber());
      cbsTemplateTransaction.setInstrumentType(x.getInstrumentType());
      cbsTemplateTransaction.setInstrumentDate(x.getInstrumentDate());
      cbsTemplateTransaction.setAccountCurrencyCode(x.getAccountCurrencyCode());
      cbsTemplateTransaction.setCurrencyCode(x.getCurrencyCode());
      cbsTemplateTransaction.setValueDate(x.getValueDate());
      cbsTemplateTransaction.setVoucherNumber(x.getVoucherNumber());
      cbsTemplateTransaction.setReferenceNumber(x.getReferenceNumber());
      cbsTemplateTransaction.setAccountBranchId(x.getAccountBranchId());
      cbsTemplateTransaction.setInitiatorBranchId(x.getInitiatorBranchId());
      cbsTemplateTransaction.setIbtaRequired(x.isIbtaRequired());
      cbsTemplateTransaction.setIbtaInformation(x.getIbtaInformation());
      cbsTemplateTransaction.setEntryUser(x.getEntryUser());
      cbsTemplateTransaction.setEntryUserTerminal(x.getEntryUserTerminal());
      cbsTemplateTransaction.setVerifyUser(x.getVerifyUser());
      cbsTemplateTransaction.setVerifyUserTerminal(x.getVerifyUserTerminal());
      cbsTemplateTransaction.setAccountCurrencyCode(x.getAccountCurrencyCode());
      return cbsTemplateTransaction;
    };
  }
}
