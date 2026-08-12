package com.gersimuca.erp.api.oauth2;

import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

public interface OAuth2ApiClient {
  @PostMapping(consumes = "application/x-www-form-urlencoded")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseEntity<TokenResponse> getToken(
      @RequestPart("grant_type") String grantType,
      @RequestPart("client_id") String clientId,
      @RequestPart(value = "username", required = false) String username,
      @RequestPart(value = "password", required = false) String password,
      @RequestPart(value = "refresh_token", required = false) String refreshToken);
}
