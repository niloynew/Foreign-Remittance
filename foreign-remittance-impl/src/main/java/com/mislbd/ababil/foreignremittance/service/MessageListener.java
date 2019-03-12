package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.broker.MessageStreams;
import com.mislbd.swift.service.AbstractMTMessageObject;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
  @StreamListener(MessageStreams.INPUT)
  public void handleGreetings(@Payload AbstractMTMessageObject msg) {
    // log.info("Received greetings: {}", msg);

    System.out.print("received message");

    System.out.println(msg.getMessageType());
  }
}
