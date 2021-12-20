package com.mislbd.ababil.foreignremittance.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.foreignremittance.domain.CustomerMerge;
import com.mislbd.ababil.foreignremittance.service.CustomerMergeService;
import com.mislbd.asset.command.api.domain.CommandSource;
import com.mislbd.asset.command.api.domain.CommandStatus;
import java.util.Map;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Responsibility:
 *
 * @author Zahid Hassan
 * @since 04-Nov-2021
 */
@Service
public class KafkaConsumer {

  private final ObjectMapper objectMapper;
  private final CustomerMergeService customerMergeService;

  public KafkaConsumer(ObjectMapper objectMapper, CustomerMergeService customerMergeService) {
    this.objectMapper = objectMapper;
    this.customerMergeService = customerMergeService;
  }

  @KafkaListener(
      topics = "${mislbd.cqrs.command.log.topic}",
      containerFactory = "customKafkaListenerContainerFactory")
  public void updateAccountPriorityOnCustomerUpdate(CommandSource commandSource) {
    System.out.println("### Received Message in group : " + commandSource);

    if (!commandSource.getStatus().equals(CommandStatus.PROCESSED)) {
      return;
    }

    if ("com.mislbd.ababil.customer.command.CreateCustomerMergeCommand"
        .equals(commandSource.commandType)) {
      actionForCustomerMergeCommand(commandSource);
    }
  }

  public void actionForCustomerMergeCommand(CommandSource commandSource) {
    try {
      Map<String, Object> jsonMap =
          objectMapper.readValue(
              commandSource.getCommand(), new TypeReference<Map<String, Object>>() {});
      CustomerMerge customerMerge =
          objectMapper.convertValue(jsonMap.get("payload"), CustomerMerge.class);

      customerMergeService.customerMerge(customerMerge);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
