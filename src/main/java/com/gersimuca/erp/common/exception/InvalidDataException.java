package com.gersimuca.erp.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends BaseException {
  public InvalidDataException(String message) {
    super(message, HttpStatus.BAD_REQUEST, ErrorSeverity.WARN);
  }
}
