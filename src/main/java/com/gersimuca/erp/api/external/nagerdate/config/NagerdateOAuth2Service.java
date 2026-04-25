package com.gersimuca.erp.api.external.nagerdate.config;

import com.gersimuca.erp.api.oauth2.OAuth2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NagerdateOAuth2Service extends OAuth2Service<NagerdateOAuth2FeignClient> {
  @Value("${feign.client.nagerdate.client-id}")
  private String clientId;

  @Value("${feign.client.nagerdate.username}")
  private String username;

  @Value("${feign.client.nagerdate.password}")
  private String password;

  public NagerdateOAuth2Service(final NagerdateOAuth2FeignClient nagerdateOAuth2FeignClient) {
    super(nagerdateOAuth2FeignClient);
  }

  @Override
  public String getAccessToken() {
    return super.getAccessToken(clientId, username, password);
  }
}
