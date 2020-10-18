package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.security.core.NgSession;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
    basePackages = {
      "com.mislbd.swift.broker",
    })
public class FeignClientConfiguration {
  private final NgSession ngSession;

  public FeignClientConfiguration(NgSession ngSession) {
    this.ngSession = ngSession;
  }
}
