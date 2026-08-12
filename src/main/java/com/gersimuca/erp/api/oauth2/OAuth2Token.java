package com.gersimuca.erp.api.oauth2;

import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class OAuth2Token {
  @Getter private final String accessToken;
  @Getter private final String refreshToken;
  private final Instant expiresAt;

  public boolean isExpired() {
    return Instant.now().isAfter(expiresAt.minusSeconds(60));
  }
}
