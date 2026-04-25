// package com.gersimuca.erp.configuration.external;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;
//
// import com.gersimuca.erp.api.oauth2.Auth2ApiClient;
// import feign.FeignException;
// import feign.Request;
// import java.util.Collections;
// import java.util.UUID;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
//
// @ExtendWith(MockitoExtension.class)
// @Disabled
// class AbstractOAuth2TokenServiceTest {
//  @Mock private Auth2ApiClient tokenClient;
//
//  private AbstractOAuth2TokenService<Auth2ApiClient> tokenService;
//
//  @BeforeEach
//  void setup() {
//    tokenService =
//        new AbstractOAuth2TokenService<>(tokenClient) {
//          @Override
//          public String getAccessToken() {
//            return UUID.randomUUID().toString();
//          }
//        };
//  }
//
//  @Test
//  void shouldReturnAccessToken_WhenTokenIsValid() {
//    Token token = new Token();
//    token.setAccessToken("mock-access-token");
//
//    ResponseEntity<Token> responseEntity = new ResponseEntity<>(token, HttpStatus.OK);
//
//    when(tokenClient.getToken(anyString(), anyString(), anyString(), anyString()))
//        .thenReturn(responseEntity);
//
//    String accessToken = tokenService.getAccessToken("client", "user", "pass");
//
//    assertEquals("mock-access-token", accessToken);
//  }
//
//  @Test
//  void shouldThrowException_WhenTokenResponseBodyIsNull() {
//    ResponseEntity<Token> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
//
//    when(tokenClient.getToken(anyString(), anyString(), anyString(), anyString()))
//        .thenReturn(responseEntity);
//
//    assertThrows(
//        IllegalStateException.class, () -> tokenService.getAccessToken("client", "user", "pass"));
//  }
//
//  @Test
//  void shouldThrowException_WhenFeignExceptionOccurs() {
//    FeignException feignException = mock(FeignException.class);
//    when(feignException.status()).thenReturn(401);
//    when(feignException.request())
//        .thenReturn(
//            Request.create(
//                Request.HttpMethod.POST, "/token", Collections.emptyMap(), null, null, null));
//
//    when(tokenClient.getToken(anyString(), anyString(), anyString(), anyString()))
//        .thenThrow(feignException);
//
//    assertThrows(
//        IllegalStateException.class, () -> tokenService.getAccessToken("client", "user", "pass"));
//  }
//
//  @Test
//  void shouldThrowException_WhenGenericExceptionOccurs() {
//    when(tokenClient.getToken(anyString(), anyString(), anyString(), anyString()))
//        .thenThrow(new RuntimeException("Something went wrong"));
//
//    assertThrows(
//        IllegalStateException.class, () -> tokenService.getAccessToken("client", "user", "pass"));
//  }
// }
