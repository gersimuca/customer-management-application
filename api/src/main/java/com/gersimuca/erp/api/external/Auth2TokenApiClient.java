package com.gersimuca.erp.api.external;

import com.gersimuca.erp.configuration.external.Token;
import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

public interface Auth2TokenApiClient {
  @PostMapping(consumes = "application/x-www-form-urlencoded")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseEntity<Token> getToken(
      @RequestPart("grant_type") String grantType,
      @RequestPart("client_id") String clientId,
      @RequestPart("username") String username,
      @RequestPart("password") String password);
}
