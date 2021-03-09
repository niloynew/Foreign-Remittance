package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.SaveSenderOrReceiverCustomerCommand;
import com.mislbd.ababil.foreignremittance.mapper.SenderOrReceiverCustomerMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SenderOrReceiverCustomerRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.SenderOrReceiverCustomerEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SenderOrReceiverCustomerCommandHandlerAggregate {

  private final SenderOrReceiverCustomerMapper senderOrReceiverCustomerMapper;
  private final SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository;
  private final Auditor auditor;

  public SenderOrReceiverCustomerCommandHandlerAggregate(
      SenderOrReceiverCustomerMapper senderOrReceiverCustomerMapper,
      SenderOrReceiverCustomerRepository senderOrReceiverCustomerRepository,
      Auditor auditor) {
    this.senderOrReceiverCustomerMapper = senderOrReceiverCustomerMapper;
    this.senderOrReceiverCustomerRepository = senderOrReceiverCustomerRepository;
    this.auditor = auditor;
  }

  @CommandListener(commandClasses = {SaveSenderOrReceiverCustomerCommand.class})
  public void auditExportLcCreate(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> saveExportLC(SaveSenderOrReceiverCustomerCommand command) {
    SenderOrReceiverCustomerEntity senderOrReceiverCustomer =
        senderOrReceiverCustomerMapper.domainToEntity().map(command.getPayload());
    senderOrReceiverCustomerRepository.save(senderOrReceiverCustomer);
    return CommandResponse.of(senderOrReceiverCustomer.getId());
  }
}
