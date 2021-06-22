package com.mislbd.ababil.foreignremittance.listener;

import com.mislbd.ababil.approvalflow.annotation.ApprovalFlowTaskListener;
import com.mislbd.ababil.approvalflow.annotation.OnStart;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.command.CreateOutwardRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.mapper.TransactionToRequestMapper;
import com.mislbd.security.core.NgSession;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.service.XmmIntegrationService;
import lombok.extern.slf4j.Slf4j;

@ApprovalFlowTaskListener
@Slf4j
public class OutwardRemittanceTaskListener {

  private final TransactionToRequestMapper transactionToRequestMapper;
  private final NgSession ngSession;
  private final XmmIntegrationService xmmIntegrationService;
  private final ConfigurationService configurationService;

  public OutwardRemittanceTaskListener(
      TransactionToRequestMapper transactionToRequestMapper,
      NgSession ngSession,
      XmmIntegrationService xmmIntegrationService,
      ConfigurationService configurationService) {
    this.transactionToRequestMapper = transactionToRequestMapper;
    this.ngSession = ngSession;
    this.xmmIntegrationService = xmmIntegrationService;
    this.configurationService = configurationService;
  }

  @OnStart(commandClass = CreateOutwardRemittanceTransactionCommand.class, priority = 1)
  public void doBeforeTransaction(CreateOutwardRemittanceTransactionCommand command) {
    RemittanceTransaction remittanceTransaction = command.getPayload();
    if (remittanceTransaction.isDoPublishMT103()) {
      publishMT103(remittanceTransaction);
    }
  }

  private void publishMT103(RemittanceTransaction remittanceTransaction) {
    MT103MessageRequest mt103MessageRequest =
        transactionToRequestMapper.mapTransactionToMessageRequest(remittanceTransaction);
    mt103MessageRequest.setEntryUser(ngSession.getUsername());
    mt103MessageRequest.setEntryUserBranch(String.valueOf(ngSession.getUserBranch()).concat("00"));
    mt103MessageRequest.setTransactionReferenceNumber(
        remittanceTransaction.getTransactionReferenceNumber());
    mt103MessageRequest.setApplicationDate(configurationService.getCurrentApplicationDate());
    MessageResponse response = xmmIntegrationService.publishCategoryNMessage(mt103MessageRequest);
    if (!response.getStatus().equals("200")) {
      log.error("## Message Publication Response ##");
      log.error(
          "Transaction Reference Number : "
              + remittanceTransaction.getTransactionReferenceNumber());
      log.error("Reason : " + response.getMessage());
      log.error("## End ##");
      remittanceTransaction.setDoPublishMT103(false);
    }
  }
}
