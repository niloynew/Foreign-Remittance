package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.repository.jpa.NostroTransactionRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroTransactionRecordEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.NostroReconcileSpecification;
import com.mislbd.ababil.foreignremittance.utils.ListResultBuilderFR;
import com.mislbd.ababil.foreignremittance.utils.PagedResultBuilderFR;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.swift.broker.model.raw.NostroTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NostroTransactionRecordServiceImpl implements NostroTransactionRecordService {

  private final NostroTransactionRepository nostroTransactionRepository;
  private final ModelMapper modelMapper;

  public NostroTransactionRecordServiceImpl(
      NostroTransactionRepository nostroTransactionRepository, ModelMapper modelMapper) {
    this.nostroTransactionRepository = nostroTransactionRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public PagedResult<NostroTransaction> getMessages(
      Pageable pageable,
      Long id,
      String accountNo,
      String advBranch,
      boolean selected,
      LocalDate valueDate) {

    return PagedResultBuilderFR.build(
        nostroTransactionRepository.findAll(
            NostroReconcileSpecification.searchSpecification(
                id, accountNo, advBranch, selected, valueDate),
            pageable),
        NostroTransaction.class);
  }

  @Override
  public List<NostroTransaction> getMessages(
      Long id, String accountNo, String advBranch, boolean selected, LocalDate valueDate) {
    return ListResultBuilderFR.build(
        nostroTransactionRepository.findAll(
            NostroReconcileSpecification.searchSpecification(
                id, accountNo, advBranch, selected, valueDate)),
        NostroTransaction.class);
  }

  @Override
  public Optional<NostroTransaction> getMessageById(long id) {
    return Optional.empty();
  }

  @Override
  @Transactional
  public void save(NostroTransaction dto) {
    nostroTransactionRepository.save(modelMapper.map(dto, NostroTransactionRecordEntity.class));
  }
}
