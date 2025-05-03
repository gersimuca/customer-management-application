package com.gersimuca.erp.common.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationNotSupportedException extends BaseException {
  public AuthenticationNotSupportedException() {
    super("Authentication not supported.", HttpStatus.UNAUTHORIZED, ErrorSeverity.ERROR);
  }
}
