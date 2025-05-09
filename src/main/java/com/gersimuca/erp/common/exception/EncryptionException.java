package com.gersimuca.erp.common.exception;

import org.springframework.http.HttpStatus;

public class EncryptionException extends BaseException {

  public EncryptionException(String message) {
    super(message, HttpStatus.INTERNAL_SERVER_ERROR, ErrorSeverity.ERROR);
  }
}
