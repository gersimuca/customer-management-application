package com.gersimuca.erp.api.oauth2;

import com.gersimuca.erp.common.exception.OAuth2Exception;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import feign.FeignException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RequiredArgsConstructor
public abstract class OAuth2Service<T extends OAuth2ApiClient> {

  private final T oAuth2ClientService;
  private final Cache<String, OAuth2Token> tokenCache =
      Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

  /** Returns a valid access token for the given client/user, refreshing if expired. */
  protected String getAccessToken(String clientId, String username, String password) {
    LoggerUtils.info(log, "Cached TOKENS: {}", tokenCache.asMap());
    String cacheKey = clientId + ":" + username;
    OAuth2Token cachedToken = tokenCache.getIfPresent(cacheKey);

    if (cachedToken != null && !cachedToken.isExpired()) {
      return cachedToken.getAccessToken();
    }

    // Fetch new token or refresh expired one
    OAuth2Token oAuth2Token = fetchOrRefreshToken(clientId, username, password, cachedToken);
    tokenCache.put(cacheKey, oAuth2Token);
    return oAuth2Token.getAccessToken();
  }

  /** Fetches a new token or refreshes the old one if a refresh token is available. */
  private OAuth2Token fetchOrRefreshToken(
      String clientId, String username, String password, OAuth2Token oldToken) {
    try {
      TokenResponse tokenResponse;

      if (oldToken != null && oldToken.getRefreshToken() != null) {
        LoggerUtils.info(log, "Refreshing token for client {} user {}", clientId, username);
        tokenResponse = refreshToken(clientId, oldToken.getRefreshToken());
      } else {
        LoggerUtils.info(log, "Fetching new token for client {} user {}", clientId, username);
        ResponseEntity<TokenResponse> response =
            oAuth2ClientService.getToken("password", clientId, username, password, null);
        tokenResponse = response.getBody();
      }

      if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
        throw new OAuth2Exception("Token response is null for client " + clientId);
      }

      // Calculate expiry
      Instant expiresAt = Instant.now().plusSeconds(tokenResponse.getExpiresIn());
      return new OAuth2Token(
          tokenResponse.getAccessToken(), tokenResponse.getRefreshToken(), expiresAt);

    } catch (FeignException fe) {
      String errorMessage =
          String.format(
              "Failed to fetch access token: %s - %d - %s",
              clientId.toUpperCase(), fe.status(), fe.request());
      LoggerUtils.error(log, errorMessage, fe);
      throw new OAuth2Exception(errorMessage, fe);
    } catch (Exception ex) {
      LoggerUtils.error(log, "Failed to fetch access token for client {}", clientId, ex);
      throw new OAuth2Exception("Unable to obtain access token for client " + clientId, ex);
    }
  }

  /** Refresh token using Keycloak API. */
  protected TokenResponse refreshToken(String clientId, String refreshToken) {
    try {
      ResponseEntity<TokenResponse> response =
          oAuth2ClientService.getToken("refresh_token", clientId, null, null, refreshToken);
      TokenResponse tokenResponse = response.getBody();

      if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
        throw new OAuth2Exception("Refresh token response is null for client " + clientId);
      }

      LoggerUtils.info(log, "Successfully refreshed token for client {}", clientId);
      return tokenResponse;

    } catch (FeignException fe) {
      String errorMessage =
          String.format(
              "Failed to refresh token: %s - %d - %s",
              clientId.toUpperCase(), fe.status(), fe.request());
      LoggerUtils.error(log, errorMessage, fe);
      throw new OAuth2Exception(errorMessage, fe);
    } catch (Exception ex) {
      LoggerUtils.error(log, "Unexpected error refreshing token for client {}", clientId, ex);
      throw new OAuth2Exception("Unable to refresh token for client " + clientId, ex);
    }
  }

  public abstract String getAccessToken();
}
