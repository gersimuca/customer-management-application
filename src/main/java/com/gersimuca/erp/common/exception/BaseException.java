package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

  private final HttpStatus httpResponseStatus;
  private final ErrorSeverity errorSeverity;
  private final ErrorCode errorCode;

  public BaseException(
      final String message,
      final HttpStatus httpResponseStatus,
      final ErrorSeverity errorSeverity,
      final ErrorCode errorCode) {

    super(message);
    this.httpResponseStatus = httpResponseStatus;
    this.errorSeverity = errorSeverity;
    this.errorCode = errorCode;
  }
}
