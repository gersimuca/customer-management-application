package com.gersimuca.erp.configuration.jpa;

import ch.qos.logback.core.testUtil.RandomUtil;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtTestData {

  public static final Set<String> ROLES = Set.of("user", "readonly", "admin", "app_erp");
  public static final String APP_NAME = "erp";
  private static final String TOKEN_VALUE = "Foo";
  private static final Map<String, Object> HEADERS = Map.of("foo", "bar");

  private static Map<String, Object> createClaims(
      String username, String givenName, String familyName, String email) {
    return Map.of(
        "preferred_username", username,
        "sub", (long) RandomUtil.getPositiveInt(),
        "resource_access", Map.of(APP_NAME, Map.of("roles", ROLES)),
        "given_name", givenName,
        "family_name", familyName,
        "email", email);
  }

  public static final String MAIL = "iron.man@marvel.com";

  public static final Jwt JWT =
      new Jwt(
          TOKEN_VALUE,
          Instant.MIN,
          Instant.MAX,
          HEADERS,
          createClaims("U123456", "Iron", "Man", MAIL));

  public static final Jwt JWT_NEW_USER =
      new Jwt(
          TOKEN_VALUE,
          Instant.MIN,
          Instant.MAX,
          HEADERS,
          createClaims("U1234567", "Man 2", "Iron", MAIL));

  public static final Jwt JWT_INVALID_USERNAME =
      new Jwt(
          TOKEN_VALUE,
          Instant.MIN,
          Instant.MAX,
          HEADERS,
          createClaims("", "Invalid", "User", MAIL));

  public static final Jwt JWT_NOT_EXISTING_USERNAME =
      new Jwt(
          TOKEN_VALUE,
          Instant.MIN,
          Instant.MAX,
          HEADERS,
          createClaims("U159785", "Invalid", "User", MAIL));

  public static final Jwt JWT_ =
      new Jwt(
          TOKEN_VALUE,
          Instant.MIN,
          Instant.MAX,
          HEADERS,
          createClaims("U159785", "Invalid", "User", MAIL));
}
