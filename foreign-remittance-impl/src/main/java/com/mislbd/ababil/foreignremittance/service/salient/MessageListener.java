package com.mislbd.ababil.foreignremittance.service.salient;

import com.mislbd.ababil.foreignremittance.broker.MessageStreams;
import com.mislbd.ababil.foreignremittance.domain.NostroReconcileDto;
import com.mislbd.ababil.foreignremittance.service.NostroReconcileService;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  private final NostroReconcileService nostroReconcileService;

  public MessageListener(NostroReconcileService nostroReconcileService) {
    this.nostroReconcileService = nostroReconcileService;
  }

  //  @KafkaListener(topics = "swift", containerFactory = "kafkaListenerContainerFactory")
  //  public void nostroReconcileDtoListener(NostroReconcileDto nostroReconcileDto) {
  //
  //    System.out.print("received message");
  //    nostroReconcileServce.save(nostroReconcileDto);
  //  }

  @StreamListener(MessageStreams.INPUT)
  public void handleGreetings(@Payload NostroReconcileDto msg) {

    System.out.print("received message");

    nostroReconcileService.save(msg);
  }
}
