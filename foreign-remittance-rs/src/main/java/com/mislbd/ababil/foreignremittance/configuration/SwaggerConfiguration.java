package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.asset.configuration.BaseSwaggerConfiguration;
import java.util.Collections;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** Responsibility: Swagger API Documentation Configurations. */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends BaseSwaggerConfiguration {

  private final BuildProperties buildProperties;

  public SwaggerConfiguration(BuildProperties buildProperties) {
    this.buildProperties = buildProperties;
  }

  @Bean
  public Docket customerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Ababil Foreign Remittance " + buildProperties.getVersion())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mislbd.ababil.foreignremittance"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(super.securitySchema()))
        .securityContexts(Collections.singletonList(super.securityContext()))
        .apiInfo(
            new ApiInfoBuilder()
                .title("Ababil Foreign Remittance API")
                .description("Ababil Foreign Remittance REST API")
                .version(buildProperties.getVersion())
                .license("© Millennium Information Solution Limited")
                .licenseUrl("http://www.mislbd.com")
                .contact(
                    new Contact(
                        "Millennium Information Solution Limited",
                        "http://www.mislbd.com",
                        "reza@mislbd.com"))
                .build());
  }

  @Bean
  public Docket documentApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("Ababil Dcoument " + buildProperties.getVersion())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mislbd.document"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(super.securitySchema()))
        .securityContexts(Collections.singletonList(super.securityContext()))
        .apiInfo(
            new ApiInfoBuilder()
                .title("Ababil Foreign Remittance API")
                .description("Ababil Foreign Remittance REST API")
                .version(buildProperties.getVersion())
                .license("© Millennium Information Solution Limited")
                .licenseUrl("http://www.mislbd.com")
                .contact(
                    new Contact(
                        "Millennium Information Solution Limited",
                        "http://www.mislbd.com",
                        "reza@mislbd.com"))
                .build());
  }
}
