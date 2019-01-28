package com.mislbd.ababil.foreignremittance.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("AbabilForeignRemittanceRepositoryConfiguration")
@EnableTransactionManagement
@EntityScan(basePackages = {"com.mislbd"})
@EnableJpaRepositories(
    basePackages = {"com.mislbd.ababil.foreignremittance.repository.jpa", "com.mislbd"})
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {}
