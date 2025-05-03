package com.gersimuca.erp.configuration.external;

import com.gersimuca.erp.api.external.Auth2TokenApiClient;
import com.gersimuca.erp.common.util.LoggerUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractOAuth2TokenService<T extends Auth2TokenApiClient> {

  private final T tokenClient;

  protected String getAccessToken(String clientId, String username, String password) {
    LoggerUtils.info(log, "Requesting access token for client {}", clientId.toUpperCase());
    try {
      ResponseEntity<Token> response =
          tokenClient.getToken("password", clientId, username, password);
      Token token = response.getBody();

      if (token == null) {
        LoggerUtils.error(log, "Token response body is null for client {}", clientId.toUpperCase());
        throw new IllegalStateException("Token response body is null");
      }

      LoggerUtils.info(log, "Received access token for client {}", clientId.toUpperCase());
      return token.getAccessToken();
    } catch (FeignException fe) {
      String errorMessage =
          String.format(
              "Failed to fetch access token: %s - %d - %s",
              clientId.toUpperCase(), fe.status(), fe.request());
      LoggerUtils.error(log, errorMessage, fe);
      throw new IllegalStateException(errorMessage, fe);
    } catch (Exception ex) {
      LoggerUtils.error(log, "Failed to fetch access token", ex);
      throw new IllegalStateException("Unable to obtain access token", ex);
    }
  }

  public abstract String getAccessToken();
}
