package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.SaveExportLCCommand;
import com.mislbd.ababil.foreignremittance.mapper.ExportLCMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ExportLCRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ExportLCEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class ExportLCCommandHandlerAggregate {

  private final ExportLCMapper exportLCMapper;
  private final ExportLCRepository exportLCRepository;

  public ExportLCCommandHandlerAggregate(
      ExportLCMapper exportLCMapper, ExportLCRepository exportLCRepository) {
    this.exportLCMapper = exportLCMapper;
    this.exportLCRepository = exportLCRepository;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> saveExportLC(SaveExportLCCommand command) {
    ExportLCEntity exportLCEntity = exportLCMapper.domainToEntity().map(command.getPayload());
    exportLCRepository.save(exportLCEntity);
    return CommandResponse.of(exportLCEntity.getId());
  }
}
