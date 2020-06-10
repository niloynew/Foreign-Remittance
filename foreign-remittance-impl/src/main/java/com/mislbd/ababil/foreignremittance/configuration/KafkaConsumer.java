package com.mislbd.ababil.foreignremittance.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mislbd.ababil.foreignremittance.command.ProcessNostroReconcileCommand;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.asset.command.api.CommandProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
      ObjectMapper objectMapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      objectMapper.setDateFormat(df);


      NostroReconcileDto reconcileDto =
          objectMapper.convertValue(consumerRecord.value(), NostroReconcileDto.class);
      if (reconcileDto != null) {
        commandProcessor.execute(new ProcessNostroReconcileCommand(reconcileDto));
      }
    } catch (Exception e) {
      LOGGER.warn(NostroReconcileDto.class.getName() + " not found.");
      e.printStackTrace();
    }

    /*JSONParser parser = new JSONParser();
      JSONObject json = (JSONObject) parser.parse(stringToParse);




      NostroReconcileDto reconcileDto =
          (NostroReconcileDto)
              JsonConverter.jsonDeserializer(NostroReconcileDto.class, consumerRecord.value().toString());
      if (reconcileDto != null) {
        commandProcessor.execute(new ProcessNostroReconcileCommand(reconcileDto));
      }
    } catch (ClassNotFoundException e) {
      LOGGER.warn(NostroReconcileDto.class.getName() + " not found.");
      e.printStackTrace();
    }*/
  }
}
