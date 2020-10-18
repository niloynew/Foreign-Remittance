package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.swift.broker.kafka.EnableSwiftKafkaConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("StartupConfiguration")
@ComponentScan({"com.mislbd"})
@EnableSwiftKafkaConfiguration
public class StartupConfiguration {}
