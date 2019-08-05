package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateIDProductCommand;
import com.mislbd.ababil.foreignremittance.exception.IDProductNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.IDProductMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.ababil.foreignremittance.service.IDProductService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class IDProductCommandHandlerAggregate {

  private final IDProductRepository productRepository;
  private final IDProductMapper productMapper;
  private final Auditor auditor;
  private final IDProductService idProductService;

  public IDProductCommandHandlerAggregate(
      IDProductRepository productRepository,
      IDProductMapper productMapper,
      Auditor auditor,
      IDProductService idProductService) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.auditor = auditor;
    this.idProductService = idProductService;
  }

  @CommandListener(commandClasses = {CreateIDProductCommand.class, UpdateIDProductCommand.class})
  public void auditIDProductCreateAndUpdate(CommandEvent e) {

    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @CommandListener(commandClasses = {DeleteIDProductCommand.class})
  public void auditIDProduct(CommandEvent e) {

    auditor.audit(
        idProductService.findIDProduct((Long) e.getCommand().getPayload()), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createIDProduct(CreateIDProductCommand command) {
    return CommandResponse.of(
        productRepository.save(productMapper.domainToEntity().map(command.getPayload())).getId());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateIDProduct(UpdateIDProductCommand command) {
    productMapper.domainToEntity().map(command.getPayload());
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteIDProduct(DeleteIDProductCommand command) {
    IDProductEntity productEntity =
        productRepository
            .findById(command.getPayload())
            .orElseThrow(IDProductNotFoundException::new);
    return CommandResponse.asVoid();
  }
}
