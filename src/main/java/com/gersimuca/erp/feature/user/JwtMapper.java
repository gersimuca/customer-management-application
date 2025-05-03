package com.gersimuca.erp.feature.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Qualifier;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtMapper {

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface FamilyNameMapper {}

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GivenNameMapper {}

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface EMailMapper {}

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface UsernameMapper {}

  @FamilyNameMapper
  static String mapFamilyName(Jwt jwt) {
    return extractCustomUserAttribute(jwt, "family_name");
  }

  @GivenNameMapper
  static String mapGivenName(Jwt jwt) {
    return extractCustomUserAttribute(jwt, "given_name");
  }

  @EMailMapper
  static String mapEMail(Jwt jwt) {
    return extractCustomUserAttribute(jwt, "email");
  }

  @UsernameMapper
  static String mapUsername(Jwt jwt) {
    return extractCustomUserAttribute(jwt, "preferred_username");
  }

  private static String extractCustomUserAttribute(Jwt jwt, String attribute) {
    return jwt.getClaimAsString(attribute);
  }
}
