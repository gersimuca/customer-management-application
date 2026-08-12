package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationNotSupportedException extends BaseException {
  public AuthenticationNotSupportedException() {
    super(
        "Authentication not supported.",
        HttpStatus.UNAUTHORIZED,
        ErrorSeverity.ERROR,
        ErrorCode.AUTHENTICATION_FAILED);
  }
}
