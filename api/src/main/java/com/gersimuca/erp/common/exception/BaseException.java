package com.gersimuca.erp.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
  private final HttpStatus httpResponseStatus;
  private final ErrorSeverity errorSeverity;

  public BaseException(String message, HttpStatus httpResponseStatus, ErrorSeverity errorSeverity) {
    super(message);
    this.httpResponseStatus = httpResponseStatus;
    this.errorSeverity = errorSeverity;
  }
}
