package com.gersimuca.erp.api.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public Logger feignLogger() {
    return new Slf4jLogger(FeignLoggers.NAGERDATE_SERVICE.name());
  }

  @Bean
  public Request.Options requestOptions() {
    return new Request.Options(5000, 15000);
  }

  @Bean
  public Retryer retryer() {
    return Retryer.NEVER_RETRY;
  }
}
