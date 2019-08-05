package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.SaveExportLCCommand;
import com.mislbd.ababil.foreignremittance.mapper.ExportLCMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.ExportLCRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.ExportLCEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class ExportLCCommandHandlerAggregate {

  private final ExportLCMapper exportLCMapper;
  private final ExportLCRepository exportLCRepository;
  private final Auditor auditor;

  public ExportLCCommandHandlerAggregate(
      ExportLCMapper exportLCMapper, ExportLCRepository exportLCRepository, Auditor auditor) {
    this.exportLCMapper = exportLCMapper;
    this.exportLCRepository = exportLCRepository;
    this.auditor = auditor;
  }

  @CommandListener(commandClasses = {SaveExportLCCommand.class})
  public void auditExportLcCreate(CommandEvent e) {

    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> saveExportLC(SaveExportLCCommand command) {
    ExportLCEntity exportLCEntity = exportLCMapper.domainToEntity().map(command.getPayload());
    exportLCRepository.save(exportLCEntity);
    return CommandResponse.of(exportLCEntity.getId());
  }
}
