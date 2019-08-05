package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.UpdateNostroReconcileCommand;
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

  @CommandListener(commandClasses = {UpdateNostroReconcileCommand.class})
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
}
