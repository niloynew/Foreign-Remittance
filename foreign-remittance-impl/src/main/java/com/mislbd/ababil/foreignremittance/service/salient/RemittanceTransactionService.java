package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;

import com.mislbd.ababil.foreignremittance.service.TransactionRegisterService;
import org.springframework.stereotype.Service;

@Service
public class RemittanceTransactionService {

  private final RemittanceTransactionMapper remittanceTransactionMapper;
  private final ConfigurationService configurationService;
  private final TransactionRegisterService transactionRegisterService;

  public RemittanceTransactionService(RemittanceTransactionMapper remittanceTransactionMapper, ConfigurationService configurationService, TransactionRegisterService transactionRegisterService) {
    this.remittanceTransactionMapper = remittanceTransactionMapper;
    this.configurationService = configurationService;
    this.transactionRegisterService = transactionRegisterService;
  }



}
