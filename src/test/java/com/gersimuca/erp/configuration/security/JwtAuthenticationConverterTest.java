package com.gersimuca.erp.configuration.security;

import static com.gersimuca.erp.configuration.jpa.JwtTestData.JWT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.util.ReflectionTestUtils;

class JwtAuthenticationConverterTest {

  private JwtAuthenticationConverter converter;

  @BeforeEach
  void setUp() {
    converter = new JwtAuthenticationConverter();
  }

  @Test
  void convert() {
    ReflectionTestUtils.setField(converter, "clientId", "erp");
    var authToken = converter.convert(JWT);
    assertThat(authToken).isNotNull();
    assertThat(authToken.getAuthorities().stream().map(GrantedAuthority::getAuthority))
        .contains("USER", "READONLY");
    assertThat(authToken.getPrincipal()).isInstanceOf(Jwt.class);
    assertThat(((Jwt) authToken.getPrincipal()).getClaimAsString("preferred_username"))
        .isEqualTo("U123456");
  }

  @Test
  void convertEmptyRoles() {
    ReflectionTestUtils.setField(converter, "clientId", "unkown");
    var authToken = converter.convert(JWT);
    assertThat(authToken.getAuthorities().stream().map(GrantedAuthority::getAuthority)).isEmpty();
  }
}
