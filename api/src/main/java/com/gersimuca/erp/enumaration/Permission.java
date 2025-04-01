package com.gersimuca.erp.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
  EMPLOYEE_READ("employee:read"),
  EMPLOYEE_UPDATE("employee:update"),
  EMPLOYEE_CREATE("employee:create"),
  EMPLOYEE_DELETE("employee:delete"),
  SYSTEM_ADMIN_READ("admin:read"),
  SYSTEM_ADMIN_UPDATE("admin:update"),
  SYSTEM_ADMIN_CREATE("admin:create"),
  SYSTEM_ADMIN_DELETE("admin:delete");

  private final String permission;
}
