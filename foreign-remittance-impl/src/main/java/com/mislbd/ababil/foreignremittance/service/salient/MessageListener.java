package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.foreignremittance.broker.MessageStreams;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.service.NostroReconcileServce;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  private final NostroReconcileServce nostroReconcileServce;

  public MessageListener(NostroReconcileServce nostroReconcileServce) {

    this.nostroReconcileServce = nostroReconcileServce;
  }

  //  @KafkaListener(topics = "swift", containerFactory = "kafkaListenerContainerFactory")
  //  public void nostroreconciledtoListener(NostroReconcileDto nostroReconcileDto) {
  //
  //    System.out.print("received message");
  //    nostroReconcileServce.save(nostroReconcileDto);
  //
  //  }

  @StreamListener(MessageStreams.INPUT)
  public void handleGreetings(@Payload NostroReconcileDto msg) {
    // log.info("Received greetings: {}", msg);

    System.out.print("received message");

    nostroReconcileServce.save(msg);
  }
}
