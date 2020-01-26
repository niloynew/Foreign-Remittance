package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoList;
import com.mislbd.ababil.foreignremittance.repository.jpa.NostroReconcileRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.NostroReconcileEntity;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class SwiftMesgCommandHandlerAggregate {

  private final NostroReconcileRepository nostroReconcileRepository;
  private final ModelMapper modelMapper;
  private final Auditor auditor;

  public SwiftMesgCommandHandlerAggregate(
      NostroReconcileRepository nostroReconcileRepository,
      ModelMapper modelMapper,
      Auditor auditor) {
    this.nostroReconcileRepository = nostroReconcileRepository;
    this.modelMapper = modelMapper;
    this.auditor = auditor;
  }

  @CommandListener(
      commandClasses = {UpdateNostroReconcileCommand.class, ProcessNostroReconcileCommand.class})
  public void auditIDProductCreateAndUpdate(CommandEvent e) {

    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateMessage(UpdateNostroReconcileCommand command) {
    nostroReconcileRepository.save(
        modelMapper.map(command.getPayload(), NostroReconcileEntity.class));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Integer> processMessage(ProcessNostroReconcileCommand command) {
    NostroReconcileDtoList dtoList = command.getPayload();
    int success = 0;
    if (dtoList.getNostroReconcileDtoList() != null
        && !dtoList.getNostroReconcileDtoList().isEmpty()) {
      for (NostroReconcileDto dto : dtoList.getNostroReconcileDtoList()) {
        try {
          nostroReconcileRepository.save(modelMapper.map(dto, NostroReconcileEntity.class));
          success++;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return CommandResponse.of(success);
  }
}
