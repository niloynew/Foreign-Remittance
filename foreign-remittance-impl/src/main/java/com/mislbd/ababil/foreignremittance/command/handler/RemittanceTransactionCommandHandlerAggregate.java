package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.SaveRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class RemittanceTransactionCommandHandlerAggregate {

  private final RemittanceTransactionRepository transactionRepository;
  private final RemittanceTransactionMapper transactionMapper;

  public RemittanceTransactionCommandHandlerAggregate(
      RemittanceTransactionRepository transactionRepository,
      RemittanceTransactionMapper transactionMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> remittanceTransactionEntry(
      SaveRemittanceTransactionCommand command) {

    return CommandResponse.of(
        transactionRepository
            .save(transactionMapper.domainToEntity().map(command.getPayload()))
            .getId());
  }
}
