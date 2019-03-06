package com.mislbd.ababil.foreignremittance.broker.config;

import com.mislbd.ababil.foreignremittance.broker.MessageStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(MessageStreams.class)
public class StreamsConfig {}
