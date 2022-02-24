// package com.mislbd.ababil.foreignremittance.configuration;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import java.util.HashMap;
// import java.util.Map;
// import org.apache.kafka.clients.consumer.ConsumerConfig;
// import org.apache.kafka.common.serialization.StringDeserializer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.annotation.EnableKafka;
// import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
// import org.springframework.kafka.core.ConsumerFactory;
// import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
// import org.springframework.kafka.support.serializer.JsonDeserializer;
//
// @Configuration
// @EnableKafka
// public class KafkaConsumerConfig {
//
//  @Value("${mislbd.kafka.bootstrap-servers}")
//  private String bootstrapServers;
//
//  @Autowired ObjectMapper objectMapper;
//
//  @Bean
//  public Map<String, Object> consumerConfigs() {
//    Map<String, Object> props = new HashMap<String, Object>();
//    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//    props.put(ConsumerConfig.GROUP_ID_CONFIG, "swift-group");
//    return props;
//  }
//
//  @Bean
//  public ConsumerFactory<String, String> consumerFactory() {
//    JsonDeserializer<Object> objectJsonDeserializer =
//        new JsonDeserializer(Object.class, this.objectMapper);
//    objectJsonDeserializer.configure(this.consumerConfigs(), false);
//    objectJsonDeserializer.addTrustedPackages(new String[] {"*"});
//    return new DefaultKafkaConsumerFactory(
//        this.consumerConfigs(), new StringDeserializer(), objectJsonDeserializer);
//  }
//
//  @Bean
//  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//    ConcurrentKafkaListenerContainerFactory<String, String> factory =
//        new ConcurrentKafkaListenerContainerFactory<String, String>();
//    factory.setConsumerFactory(consumerFactory());
//    return factory;
//  }
//
//  //  @Bean
//  //  public KafkaConsumer kafkaConsumer() {
//  //    return new KafkaConsumer();
//  //  }
// }
