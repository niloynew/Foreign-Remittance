package com.mislbd.ababil.foreignremittance.configuration;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(
      topics = "swift-nostro-msg",
      clientIdPrefix = "json",
      containerFactory = "kafkaListenerContainerFactory")
  public void receiveMessage(ConsumerRecord<String, Object> consumerRecord) {
    LOGGER.info("received message='{}'", consumerRecord.key() + consumerRecord.value().toString());
  }
}
