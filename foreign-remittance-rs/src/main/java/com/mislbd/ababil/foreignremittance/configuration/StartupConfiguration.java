package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.security.policy.client.annotation.EnableAbabilPolicy;
import com.mislbd.swift.broker.kafka.EnableSwiftKafkaConfiguration;
import com.mislbd.transaction.api.transaction.annotation.EnableCbsTransaction;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("StartupConfiguration")
@ComponentScan({"com.mislbd"})
@EnableSwiftKafkaConfiguration
@EnableAbabilPolicy
@EnableCbsTransaction
public class StartupConfiguration {}
