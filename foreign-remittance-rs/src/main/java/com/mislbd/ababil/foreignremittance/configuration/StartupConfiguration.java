package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.security.policy.client.annotation.EnableAbabilPolicy;
import com.mislbd.swift.broker.kafka.EnableSwiftKafkaConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("StartupConfiguration")
@ComponentScan({"com.mislbd"})
@EnableSwiftKafkaConfiguration
@EnableAbabilPolicy
public class StartupConfiguration {}
