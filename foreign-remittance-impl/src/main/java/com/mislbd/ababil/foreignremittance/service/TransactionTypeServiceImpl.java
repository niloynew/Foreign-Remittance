package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.RemittanceType;
import com.mislbd.ababil.foreignremittance.domain.TransactionType;
import com.mislbd.ababil.foreignremittance.exception.ForeignRemittanceBaseException;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceTransactionMapper;
import com.mislbd.ababil.foreignremittance.mapper.TransactionTypeMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.RemittanceTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionBanksRepository;
import com.mislbd.ababil.foreignremittance.repository.jpa.TransactionTypeRepository;
import com.mislbd.ababil.foreignremittance.repository.specification.TransactionTypeSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

  private final TransactionTypeRepository transactionTypeRepository;
  private final NostroTransactionRepository nostroTransactionRepository;
  private final TransactionBanksRepository transactionBanksRepository;
  private final TransactionTypeMapper transactionTypeMapper;
  // private final NostroTransactionMapper nostroTransactionMapper;

  public TransactionTypeServiceImpl(
      TransactionTypeRepository transactionTypeRepository,
      RemittanceTransactionRepository remittanceTransactionRepository,
      TransactionBanksRepository transactionBanksRepository,
      TransactionTypeMapper transactionTypeMapper,
      RemittanceTransactionMapper remittanceTransactionMapper,
      NostroTransactionRepository nostroTransactionRepository) {
    this.transactionTypeRepository = transactionTypeRepository;
    this.nostroTransactionRepository = nostroTransactionRepository;

    this.transactionBanksRepository = transactionBanksRepository;
    this.transactionTypeMapper = transactionTypeMapper;
  }

  @Override
  public PagedResult<TransactionType> getTypes(
      Pageable pageable, Long id, RemittanceType remittanceType) {
    return PagedResultBuilder.build(
        transactionTypeRepository.findAll(
            TransactionTypeSpecification.findTransactionTypes(id, remittanceType), pageable),
        transactionTypeMapper.entityToDomain());
  }

  @Override
  public List<TransactionType> getTypes(Long id, RemittanceType remittanceType) {
    return ListResultBuilder.build(
        transactionTypeRepository.findAll(
            TransactionTypeSpecification.findTransactionTypes(id, remittanceType)),
        transactionTypeMapper.entityToDomain());
  }

  @Override
  public TransactionType typeForAdvanceRemittance() {
    return transactionTypeMapper
        .entityToDomain()
        .map(
            transactionTypeRepository
                .findByNameIsLike("Advanced Receive Agt. Export")
                .orElseThrow(
                    () ->
                        new ForeignRemittanceBaseException(
                            "Transaction type not found for Advanced Receive Agt. Export")));
  }
}
//  @Override
//  public List<SwiftBankConfiguration> getConfigurations (Long id){
//    //NostroTransaction nostroTransaction =
// nostroTransactionRepository.findById(id).orElseThrow(RemittanceTransactionNotFoundException::new);
//    //transactionBanksRepository.findByNostroTransaction(remittanceTransaction)
//  //}
// }
