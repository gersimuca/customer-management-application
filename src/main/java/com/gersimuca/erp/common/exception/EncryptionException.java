package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class EncryptionException extends BaseException {

  public EncryptionException(final String message) {
    super(
        message, HttpStatus.INTERNAL_SERVER_ERROR, ErrorSeverity.ERROR, ErrorCode.ENCRYPTION_ERROR);
  }
}
