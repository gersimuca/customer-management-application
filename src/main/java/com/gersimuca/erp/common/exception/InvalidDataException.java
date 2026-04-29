package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidDataException extends BaseException {
  public InvalidDataException(final String message) {
    super(message, HttpStatus.BAD_REQUEST, ErrorSeverity.WARN, ErrorCode.INVALID_REQUEST);
  }
}
