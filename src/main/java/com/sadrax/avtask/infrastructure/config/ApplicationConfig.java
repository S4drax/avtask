package com.sadrax.avtask.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.net.http.HttpClient;

@ConfigurationPropertiesScan(value = "com.sadrax.avtask")
@ComponentScan(
    value = {"com.sadrax.avtask"})
@EnableAspectJAutoProxy
@Configuration
public class ApplicationConfig {

  @Bean
  ForwardedHeaderFilter forwardedHeaderFilter() {
    return new ForwardedHeaderFilter();
  }
  @Bean
  public HttpClient httpClient() {
    return HttpClient.newHttpClient();
  }
}
