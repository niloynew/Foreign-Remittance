package com.mislbd.ababil.foreignremittance.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroTransactionCommand;
import com.mislbd.ababil.foreignremittance.command.UpdateSwiftRegisterCommand;
import com.mislbd.ababil.foreignremittance.domain.SwiftRegister;
import com.mislbd.ababil.foreignremittance.mapper.SwiftRegisterMapper;
import com.mislbd.ababil.foreignremittance.service.SwiftRegisterService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.swift.broker.model.RouteResponse;
import com.mislbd.swift.broker.model.raw.NostroAccountTransactionsDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  @Autowired private CommandProcessor commandProcessor;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private SwiftRegisterService swiftRegisterService;
  @Autowired private SwiftRegisterMapper swiftRegisterMapper;

  @KafkaListener(topics = "swift-nostro-txn", groupId = "swift-group")
  public void receiveMessage(ConsumerRecord<String, Object> consumerRecord) {
    LOGGER.info("received message='{}'", consumerRecord.key());
    try {
      NostroAccountTransactionsDto dtoResponse =
          objectMapper.convertValue(consumerRecord.value(), NostroAccountTransactionsDto.class);
      if (dtoResponse != null && !dtoResponse.getNostroAccountTransactionList().isEmpty()) {
        commandProcessor.execute(new ProcessNostroTransactionCommand(dtoResponse));
      }
    } catch (Exception e) {
      LOGGER.warn(NostroAccountTransactionsDto.class.getName() + " not found.");
      e.printStackTrace();
    }
  }

  @KafkaListener(topics = "swift-routing-status", groupId = "swift-group")
  public void receiveRoutingMessage(ConsumerRecord<String, Object> consumerRecord) {
    LOGGER.info("received message='{}'", consumerRecord.key());
    try {
      RouteResponse routeResponse =
          objectMapper.convertValue(consumerRecord.value(), RouteResponse.class);
      if (routeResponse != null) {
        SwiftRegister swiftRegister =
            swiftRegisterService.findByReferenceNumber(routeResponse.getReferenceNumber());
        swiftRegister.setStatus(routeResponse.getStatus());
        commandProcessor.executeResult(new UpdateSwiftRegisterCommand(swiftRegister));
      }
    } catch (Exception e) {
      LOGGER.warn(RouteResponse.class.getName() + " not found.");
      e.printStackTrace();
    }
  }
}
