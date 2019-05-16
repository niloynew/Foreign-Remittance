package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroReconcileRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import com.mislbd.ababil.foreignremittance.repository.specification.NostroReconcileSpecification;
import com.mislbd.ababil.foreignremittance.utils.PagedResultBuilderFR;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NostroReconcileServiceImpl implements NostroReconcileServce {

  private final NostroReconcileRepository nostroReconcileRepository;
  private final ModelMapper modelMapper;

  public NostroReconcileServiceImpl(
      NostroReconcileRepository nostroReconcileRepository, ModelMapper modelMapper) {
    this.nostroReconcileRepository = nostroReconcileRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public PagedResult<NostroReconcileDto> getMessages(
      Pageable pageable, Long id, String accountNo, LocalDate valueDate) {

    // Page<NostroReconcileEntity> messages =
    return PagedResultBuilderFR.build(
        nostroReconcileRepository.findAll(
            NostroReconcileSpecification.searchSpecification(id, accountNo, valueDate), pageable),
        NostroReconcileDto.class);
  }

  @Override
  public Optional<NostroReconcileDto> getMessageById(long id) {
    return Optional.empty();
  }

  @Override
  @Transactional
  public void save(NostroReconcileDto dto) {
    nostroReconcileRepository.save(modelMapper.map(dto, NostroReconcileEntity.class));
  }
}
