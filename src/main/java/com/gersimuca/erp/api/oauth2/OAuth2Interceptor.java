package com.gersimuca.erp.api.oauth2;

import com.gersimuca.erp.common.exception.OAuth2Exception;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuth2Interceptor implements RequestInterceptor {
  private final Supplier<String> tokenSupplier;

  @Override
  public void apply(RequestTemplate template) {
    String token = tokenSupplier.get();
    if (token == null) {
      throw new OAuth2Exception("OAuth2 token is null");
    }
    template.header("Authorization", String.format("Bearer %s", token));
  }
}
