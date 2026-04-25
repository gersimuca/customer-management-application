package com.gersimuca.erp.common.exception;

import org.springframework.http.HttpStatus;

public class OAuth2Exception extends BaseException {

  public OAuth2Exception(String message) {
    super(message, HttpStatus.UNAUTHORIZED, ErrorSeverity.ERROR);
  }

  public OAuth2Exception(String message, Throwable cause) {
    super(message, HttpStatus.UNAUTHORIZED, ErrorSeverity.ERROR);
    initCause(cause);
  }
}
