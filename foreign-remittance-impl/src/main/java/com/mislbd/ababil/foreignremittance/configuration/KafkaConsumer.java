package com.mislbd.ababil.foreignremittance.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBroker;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoBrokerList;
import com.mislbd.asset.command.api.CommandProcessor;
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

  @KafkaListener(
      topics = "swift-nostro-msg",
      clientIdPrefix = "json",
      containerFactory = "kafkaListenerContainerFactory")
  public void receiveMessage(ConsumerRecord<String, Object> consumerRecord) {
    LOGGER.info("received message='{}'", consumerRecord.key() + consumerRecord.value().toString());
    try {

      NostroReconcileDtoBrokerList reconcileDtoList =
          objectMapper.convertValue(consumerRecord.value(), NostroReconcileDtoBrokerList.class);
      if (reconcileDtoList != null
          && !reconcileDtoList.getNostroReconcileDtoBrokerList().isEmpty()) {

        commandProcessor.execute(new ProcessNostroReconcileCommand(reconcileDtoList));
      }
    } catch (Exception e) {
      LOGGER.warn(NostroReconcileDtoBroker.class.getName() + " not found.");
      e.printStackTrace();
    }
  }
}
