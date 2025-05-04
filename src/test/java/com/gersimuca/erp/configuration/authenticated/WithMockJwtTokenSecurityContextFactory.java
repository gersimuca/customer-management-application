package com.gersimuca.erp.configuration.authenticated;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

final class WithMockJwtTokenSecurityContextFactory
        implements WithSecurityContextFactory<WithMockJwtToken> {
  @Override
  public SecurityContext createSecurityContext(WithMockJwtToken annotation) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    Authentication authentication = createAuthentication(annotation);
    context.setAuthentication(authentication);

    return context;
  }

  private Authentication createAuthentication(WithMockJwtToken withMockJwtToken) {
    Jwt jwt = createJwt(withMockJwtToken);

    Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(withMockJwtToken.authorities()).map(SimpleGrantedAuthority::new).toList();

    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(jwt, null, authorities);
    authenticationToken.setDetails(jwt);

    return authenticationToken;
  }

  private Jwt createJwt(WithMockJwtToken withMockJwtToken) {
    return Jwt.withTokenValue("test-token")
            .subject(withMockJwtToken.username())
            .issuer("test-issuer")
            .claim("preferred_username", withMockJwtToken.username())
            .claim("authorities", withMockJwtToken.authorities())
            .header("test-key", "test-value")
            .build();
  }
}
