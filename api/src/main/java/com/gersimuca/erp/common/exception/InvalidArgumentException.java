package com.gersimuca.erp.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BaseException {
  public InvalidArgumentException(String message) {
    super(message, HttpStatus.BAD_REQUEST, ErrorSeverity.WARN);
  }
}
