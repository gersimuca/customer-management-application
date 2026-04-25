package com.gersimuca.erp.api.oauth2;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuth2Config {
  @Bean
  public Encoder feignFormEncoder() {
    return new SpringFormEncoder();
  }
}
