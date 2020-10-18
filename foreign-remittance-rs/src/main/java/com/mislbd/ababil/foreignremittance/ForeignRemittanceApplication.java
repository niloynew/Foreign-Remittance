package com.mislbd.ababil.foreignremittance;

import com.mislbd.swift.broker.kafka.EnableSwiftKafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwiftKafkaConfiguration
@SpringBootApplication
public class ForeignRemittanceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ForeignRemittanceApplication.class, args);
  }
}
