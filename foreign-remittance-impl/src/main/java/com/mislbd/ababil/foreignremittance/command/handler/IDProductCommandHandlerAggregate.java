package com.mislbd.ababil.foreignremittance.command.handler;

import com.mislbd.ababil.foreignremittance.command.CreateIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.DeleteIDProductCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateIDProductCommand;
import com.mislbd.ababil.foreignremittance.exception.IDProductNotFoundException;
import com.mislbd.ababil.foreignremittance.mapper.IDProductMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.IDProductRepository;
import com.mislbd.ababil.foreignremittance.repository.schema.IDProductEntity;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class IDProductCommandHandlerAggregate {

  private final IDProductRepository productRepository;
  private final IDProductMapper productMapper;

  public IDProductCommandHandlerAggregate(
      IDProductRepository productRepository, IDProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
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
