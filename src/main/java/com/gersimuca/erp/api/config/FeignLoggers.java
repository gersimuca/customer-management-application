package com.gersimuca.erp.api.config;

import lombok.Getter;

/**
 * @author gersimuca
 */
@Getter
public enum FeignLoggers {
  NAGERDATE_SERVICE("nagerdate-service"),
  KEYCLOACK_SERVICE("keycloak-service");

  private final String value;

  FeignLoggers(final String value) {
    this.value = value;
  }
}
