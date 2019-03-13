package com.mislbd.ababil.foreignremittance.service;

import com.mislbd.ababil.foreignremittance.broker.MessageStreams;
import com.mislbd.ababil.foreignremittance.domain.RemittanceMsgDto;
import com.mislbd.ababil.foreignremittance.mapper.RemittanceMsgDtoMapper;
import com.mislbd.ababil.foreignremittance.repository.jpa.SwiftMsgRepository;
//import com.mislbd.swift.service.AbstractMTMessageObject;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  private final SwiftMsgRepository swiftMsgRepository;
  private final RemittanceMsgDtoMapper mapper;

  public MessageListener(SwiftMsgRepository swiftMsgRepository, RemittanceMsgDtoMapper mapper) {
    this.swiftMsgRepository = swiftMsgRepository;
    this.mapper = mapper;
  }

  @StreamListener(MessageStreams.INPUT)
  public void handleGreetings(@Payload RemittanceMsgDto msg) {
    // log.info("Received greetings: {}", msg);

    System.out.print("received message");

   swiftMsgRepository.save(mapper.domainToEntity().map(msg));
  }
}
