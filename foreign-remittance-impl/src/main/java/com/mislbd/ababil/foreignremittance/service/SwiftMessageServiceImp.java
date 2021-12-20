package com.mislbd.ababil.foreignremittance.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.approvalflow.repository.jpa.ApprovalFlowTaskInstanceRepository;
import com.mislbd.ababil.approvalflow.service.ApprovalFlowTaskInstanceService;
import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.foreignremittance.domain.RemittanceMessageDto;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.domain.XmmMessageType;
import com.mislbd.ababil.foreignremittance.domain.XmmRequest;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.mapper.TransactionToRequestMapper;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.security.core.NgSession;
import com.mislbd.swift.broker.model.MessageResponse;
import com.mislbd.swift.broker.model.raw.mt1xx.MT103MessageRequest;
import com.mislbd.swift.broker.service.XmmIntegrationService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SwiftMessageServiceImp implements SwiftMsgService {
  private final ApprovalFlowTaskInstanceRepository approvalFlowTaskInstanceRepository;
  private final ApprovalFlowTaskInstanceService approvalFlowTaskInstanceService;
  private final ObjectMapper objectMapper;
  private final TransactionToRequestMapper transactionToRequestMapper;
  private final XmmIntegrationService xmmIntegrationService;
  private final NgSession ngSession;
  private final ConfigurationService configurationService;
  private final RemittanceTransactionService remittanceTransactionService;

  public SwiftMessageServiceImp(
      ApprovalFlowTaskInstanceRepository approvalFlowTaskInstanceRepository,
      ApprovalFlowTaskInstanceService approvalFlowTaskInstanceService,
      ObjectMapper objectMapper,
      TransactionToRequestMapper transactionToRequestMapper,
      XmmIntegrationService xmmIntegrationService,
      NgSession ngSession,
      ConfigurationService configurationService,
      RemittanceTransactionService remittanceTransactionService) {
    this.approvalFlowTaskInstanceRepository = approvalFlowTaskInstanceRepository;
    this.approvalFlowTaskInstanceService = approvalFlowTaskInstanceService;
    this.objectMapper = objectMapper;
    this.transactionToRequestMapper = transactionToRequestMapper;
    this.xmmIntegrationService = xmmIntegrationService;
    this.ngSession = ngSession;
    this.configurationService = configurationService;
    this.remittanceTransactionService = remittanceTransactionService;
  }

  @Override
  public PagedResult<RemittanceMessageDto> findAll(
      Pageable pageable, String msgType, String lcNo, BigDecimal amount, LocalDate valueDate) {
    return null;
  }

  @Override
  public Optional<RemittanceMessageDto> findByID(Long id) {
    return Optional.empty();
  }

  @Override
  public RemittanceMessageDto findByLCNo(String lcNo) {
    return null;
  }

  @Override
  public int process(List<Long> msgIds) {
    return 0;
  }

  @Override
  public String getSwiftMiddlewareUrl() {
    return null;
  }

  @Override
  public MessageResponse generateSwiftMT103(XmmRequest xmmRequest) throws IOException {
    if (xmmRequest.getMessageType() == XmmMessageType.MT103) {
      return processMT103(xmmRequest);
    } else {
      throw new ForeignRemittanceBaseException("XmmMessageType not found, please check.");
    }
  }

  private MessageResponse processMT103(XmmRequest xmmRequest) throws IOException {
    if (approvalFlowTaskInstanceRepository
        .findFirstByDomainGroupAndDomainReference(
            xmmRequest.getDomainGroup(), xmmRequest.getReferenceNumber())
        .isPresent()) {

      long Id =
          approvalFlowTaskInstanceRepository
              .findFirstByDomainGroupAndDomainReference(
                  xmmRequest.getDomainGroup(), xmmRequest.getReferenceNumber())
              .get()
              .getId();

      RemittanceTransaction remittanceTransaction =
          objectMapper.readValue(
              approvalFlowTaskInstanceService.getApprovalFlowTaskInstancePayload(Id).get(),
              RemittanceTransaction.class);

      MT103MessageRequest mt103MessageRequest =
          transactionToRequestMapper.mapTransactionToMessageRequest(remittanceTransaction);
      mt103MessageRequest.setEntryUser(ngSession.getUsername());
      mt103MessageRequest.setEntryUserBranch(
          String.valueOf(ngSession.getUserBranch()).concat("00"));
      mt103MessageRequest.setTransactionReferenceNumber(
          remittanceTransaction.getTransactionReferenceNumber());
      mt103MessageRequest.setApplicationDate(configurationService.getCurrentApplicationDate());
      return xmmIntegrationService.publishCategoryNMessage(mt103MessageRequest);
    } else if (remittanceTransactionService
        .findTransaction(xmmRequest.getReferenceNumber())
        .isPresent()) {
      return xmmIntegrationService.authorizeCategoryNMessage(xmmRequest.getReferenceNumber());
    } else {
      throw new ForeignRemittanceBaseException(
          "Outward transaction not found with reference number " + xmmRequest.getReferenceNumber());
    }
  }
}
