package com.gersimuca.erp.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationExpressions {
  public static final String IS_ADMIN = "hasAuthority('ADMIN')";
  public static final String IS_AUTHORIZED = "hasAuthority('APP_ERP')";
  public static final String HAS_ANY_AUTHORITY_ADMIN_CMS_KAM =
      "hasAnyAuthority('ADMIN','CMS','KAM')";
  public static final String HAS_ANY_AUTHORITY_ADMIN_KAM = "hasAnyAuthority('ADMIN','KAM')";
}
