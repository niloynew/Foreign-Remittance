package com.mislbd.ababil.foreignremittance.broker.config;

import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaStreamsConfig {
  @Bean
  public ConsumerFactory<String, NostroReconcileDto> consumerFactory() {
    Map<String, Object> config = new HashMap<>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.140:9092");
    config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    config.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 132000);
    config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 132000);
    config.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 132001);
    config.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
    config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 20);

    return new DefaultKafkaConsumerFactory<>(
        config, new StringDeserializer(), new JsonDeserializer<>(NostroReconcileDto.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, NostroReconcileDto>
      kafkaListenerContainerFactory() {
    /*  ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
    factory.setConsumerFactory(consumerFactory());
    return factory;*/
    ConcurrentKafkaListenerContainerFactory<String, NostroReconcileDto> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
