package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.SaveRemittanceTransactionCommand;
import com.mislbd.ababil.foreignremittance.domain.BankInformation;
import com.mislbd.ababil.foreignremittance.domain.RemittanceTransaction;
import com.mislbd.ababil.foreignremittance.mapper.BankInformationMapper;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.BankInformationRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.BankInformationEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class RemittanceTransactionCommandHandlerAggregate {

  private final RemittanceTransactionRepository transactionRepository;
  private final RemittanceTransactionMapper transactionMapper;
  private final BankInformationRepository bankInformationRepository;
  private final BankInformationMapper bankInformationMapper;

  public RemittanceTransactionCommandHandlerAggregate(
      RemittanceTransactionRepository transactionRepository,
      RemittanceTransactionMapper transactionMapper,
      BankInformationRepository bankInformationRepository,
      BankInformationMapper bankInformationMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
    this.bankInformationRepository = bankInformationRepository;
    this.bankInformationMapper = bankInformationMapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> remittanceTransactionEntry(
      SaveRemittanceTransactionCommand command) {

    /*
     * Save entries in RemittanceTransaction table
     * Save entries in BankInformation table
     * transaction begin for inward remittance
     * transaction from inward remittance charge by remittanceChargeInformationList
     * */
    Long globalTxnNumber = null;
    RemittanceTransaction remittanceTransaction = command.getPayload();
    Long remittanceTxnId =
        transactionRepository
            .save(transactionMapper.domainToEntity().map(remittanceTransaction))
            .getId();
    List<BankInformation> bankInformationList = command.getPayload().getBankInformation();
    if (!bankInformationList.isEmpty())
      bankInformationList.forEach(
          bankInformation -> {
            BankInformationEntity bankInformationEntity =
                bankInformationMapper.domainToEntity().map(bankInformation);
            bankInformationEntity.setRemittanceTransaction(
                transactionRepository.getOne(remittanceTxnId));
            bankInformationRepository.save(bankInformationEntity);
          });
    switch (remittanceTransaction.getDebitAccountType()) {
      case GL:
        //                globalTxnNumber = transactionService.doGlTransaction(new
        // GlTransactionRequest()
        //                        .setDebitTransaction(false)
        //                        .setAmountCcy(remittanceTransaction.getAmountLcy())
        //                        .setAmountCcy(remittanceTransaction.getAmountFcy())
        //                        .setGlobalTxnNo(null)
        //                        .setCurrencyCode(remittanceTransaction.getCurrencyCode())
        //                        .setActivityId(remittanceTransaction.getTransactionTypeId())
        //                        , TransactionRequestType.TRANSFER).getGlobalTxnNo();
        break;
      case CASA:
        break;
    }

    return CommandResponse.of(globalTxnNumber);
  }
}
