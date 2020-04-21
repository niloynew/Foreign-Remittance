package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDtoList;
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

  @KafkaListener(
      topics = "swift-nostro-msg",
      clientIdPrefix = "json",
      containerFactory = "kafkaListenerContainerFactory")
  public void receiveMessage(ConsumerRecord<String, Object> consumerRecord) {
    LOGGER.info("received message='{}'", consumerRecord.key() + consumerRecord.value().toString());
    try {
      NostroReconcileDtoList reconcileDtoList =
          (NostroReconcileDtoList)
              JsonConverter.jsonDeserializer(
                  NostroReconcileDtoList.class, consumerRecord.value().toString());
      if (reconcileDtoList != null && !reconcileDtoList.getNostroReconcileDtoList().isEmpty()) {
        commandProcessor.execute(new ProcessNostroReconcileCommand(reconcileDtoList));
      }
    } catch (ClassNotFoundException e) {
      LOGGER.warn(NostroReconcileDto.class.getName() + " not found.");
      e.printStackTrace();
    }
  }
}
