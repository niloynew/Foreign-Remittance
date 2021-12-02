package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.kafka.configuration.AbabilKafkaConsumerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

/**
 * Responsibility:
 *
 * @author Zahid Hassan
 * @since 04-Nov-2021
 */
@Configuration
public class KafkaConfiguration extends AbabilKafkaConsumerConfiguration {

  private final String customerMergeCommand =
      "com.mislbd.ababil.customer.command.CreateCustomerMergeCommand";

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object>
      customKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.RECORD);
    factory.setRecordFilterStrategy(
        consumerRecord ->
            consumerRecord.key() == null || !consumerRecord.key().equals(customerMergeCommand));
    return factory;
  }
}
