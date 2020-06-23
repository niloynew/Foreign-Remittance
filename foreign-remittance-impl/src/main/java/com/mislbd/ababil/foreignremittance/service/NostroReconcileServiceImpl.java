package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroReconcileRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.NostroReconcileSpecification;
import com.mislbd.ababil.foreignremittance.utils.ListResultBuilderFR;
import com.mislbd.ababil.foreignremittance.utils.PagedResultBuilderFR;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NostroReconcileServiceImpl implements NostroReconcileService {

  private final NostroReconcileRepository nostroReconcileRepository;
  private final ModelMapper modelMapper;

  public NostroReconcileServiceImpl(
      NostroReconcileRepository nostroReconcileRepository, ModelMapper modelMapper) {
    this.nostroReconcileRepository = nostroReconcileRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public PagedResult<NostroReconcileDtoBroker> getMessages(
      Pageable pageable,
      Long id,
      String accountNo,
      String advBranch,
      boolean selected,
      LocalDate valueDate) {

    // Page<NostroReconcileEntity> messages =
    return PagedResultBuilderFR.build(
        nostroReconcileRepository.findAll(
            NostroReconcileSpecification.searchSpecification(
                id, accountNo, advBranch, selected, valueDate),
            pageable),
        NostroReconcileDtoBroker.class);
  }

  @Override
  public List<NostroReconcileDtoBroker> getMessages(
      Long id, String accountNo, String advBranch, boolean selected, LocalDate valueDate) {
    return ListResultBuilderFR.build(
        nostroReconcileRepository.findAll(
            NostroReconcileSpecification.searchSpecification(
                id, accountNo, advBranch, selected, valueDate)),
        NostroReconcileDtoBroker.class);
  }

  @Override
  public Optional<NostroReconcileDtoBroker> getMessageById(long id) {
    return Optional.empty();
  }

  @Override
  @Transactional
  public void save(NostroReconcileDtoBroker dto) {
    nostroReconcileRepository.save(modelMapper.map(dto, NostroReconcileEntity.class));
  }
}
