package com.gersimuca.erp.api.config;

import feign.Logger;
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
    return new Slf4jLogger("Feign");
  }
  //
  //  @Bean
  //  public feign.Request.Options options() {
  //    return new feign.Request.Options(
  //            5000,
  //            15000);
  //  }
  //
  //  @Bean
  //  public Retryer feignRetryer() {
  //    return new Retryer.Default(100, 500, 3);
  //  }
  //
  ////  @Bean
  ////  public Encoder feignFormEncoder() {
  ////    return new SpringEncoder(HttpMessageConverters::new);
  ////  }
  //
  //  @Bean
  //  public Decoder feignDecoder() {
  //    return new OptionalDecoder(new SpringDecoder(HttpMessageConverters::new));
  //  }
}
