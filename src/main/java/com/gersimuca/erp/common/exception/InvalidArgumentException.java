package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BaseException {
  public InvalidArgumentException(final String message) {
    super(message, HttpStatus.BAD_REQUEST, ErrorSeverity.WARN, ErrorCode.INVALID_REQUEST);
  }
}
