package com.gersimuca.erp.api.external.nagerdate.config;

import com.gersimuca.erp.api.oauth2.OAuth2Interceptor;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class NagerdateConfig {
  @Bean
  public RequestInterceptor nagerdateInterceptor(NagerdateOAuth2Service oAuth2Service) {
    return new OAuth2Interceptor(oAuth2Service::getAccessToken);
  }
}
