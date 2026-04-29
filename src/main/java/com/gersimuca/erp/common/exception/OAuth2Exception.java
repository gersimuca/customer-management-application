package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class OAuth2Exception extends BaseException {

  public OAuth2Exception(final String message) {
    super(message, HttpStatus.UNAUTHORIZED, ErrorSeverity.ERROR, ErrorCode.ACCESS_DENIED);
  }

  public OAuth2Exception(final String message, final Throwable cause) {
    super(message, HttpStatus.UNAUTHORIZED, ErrorSeverity.ERROR, ErrorCode.ACCESS_DENIED);
    initCause(cause);
  }
}
