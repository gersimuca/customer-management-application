package com.gersimuca.erp.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationProvider {

  public static Authentication getAuthenticationFromContext() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static String getPreferredUsernameFromContext() {
    return ((Jwt) getAuthenticationFromContext().getPrincipal())
        .getClaimAsString("preferred_username");
  }
}
