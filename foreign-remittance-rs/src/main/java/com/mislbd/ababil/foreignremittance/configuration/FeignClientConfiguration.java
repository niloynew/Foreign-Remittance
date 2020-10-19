package com.mislbd.ababil.foreignremittance.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
    basePackages = {
      "com.mislbd",
    })
public class FeignClientConfiguration {}
