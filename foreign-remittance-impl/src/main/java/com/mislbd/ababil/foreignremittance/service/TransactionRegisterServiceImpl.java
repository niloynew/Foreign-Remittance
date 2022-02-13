package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.CbsTemplateTransaction;
import com.mislbd.ababil.foreignremittance.domain.TransactionRegister;
import com.mislbd.ababil.foreignremittance.mapper.TransactionRegisterMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionRegisterRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.TransactionRegisterEntity;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TransactionRegisterServiceImpl implements TransactionRegisterService {

  private final TransactionRegisterRepository transactionRegisterRepository;
  private final TransactionRegisterMapper transactionRegisterMapper;

  public TransactionRegisterServiceImpl(
      TransactionRegisterRepository transactionRegisterRepository,
      TransactionRegisterMapper transactionRegisterMapper) {
    this.transactionRegisterRepository = transactionRegisterRepository;
    this.transactionRegisterMapper = transactionRegisterMapper;
  }

  @Override
  public void doRegister(
      List<CbsTemplateTransaction> transactions, Long voucherNumber, Long remittanceTransactionId) {
    List<TransactionRegisterEntity> registerEntities = new ArrayList<>();
    if (transactions != null && !transactions.isEmpty() && remittanceTransactionId != null) {
      transactions.forEach(
          txn -> {
            TransactionRegisterEntity registerEntity =
                transactionRegisterMapper.cbsDomainEntity(voucherNumber).map(txn);
            registerEntity.setRemittanceTransactionId(remittanceTransactionId);
            registerEntity.setValid(true);
            registerEntities.add(registerEntity);
          });
    }
    if (!registerEntities.isEmpty()) {
      transactionRegisterRepository.saveAll(registerEntities);
    }
  }

  @Override
  public List<TransactionRegister> findTransactionRegisterByGlobalTxnNumber(Long globalTxnNumber) {
    return ListResultBuilder.build(
        transactionRegisterRepository.findAllByVoucherNumber(globalTxnNumber),
        transactionRegisterMapper.entityToDomain());
  }

  @Override
  public void invalidRegister(Long globalTxnNumber) {
    List<TransactionRegisterEntity> registers =
        transactionRegisterRepository.findAllByVoucherNumber(globalTxnNumber);
    registers =
        registers.stream().map(register -> register.setValid(false)).collect(Collectors.toList());
    transactionRegisterRepository.saveAll(registers);
  }

  @Override
  public List<TransactionRegister> findTransactionRegisterByRemittanceTransaction(
      Long remittanceTransactionId) {
    return ListResultBuilder.build(
        transactionRegisterRepository.findAllByRemittanceTransactionId(remittanceTransactionId),
        transactionRegisterMapper.entityToDomain());
  }
}
