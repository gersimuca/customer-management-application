package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

  private static final HttpStatus NOT_FOUND_RESPONSE_STATUS = HttpStatus.NOT_FOUND;
  private static final ErrorSeverity ERROR_SEVERITY = ErrorSeverity.WARN;

  public EntityNotFoundException(Class<?> entityClass, Long id) {
    super(
        format("%s with id %d not found", entityClass.getSimpleName(), id),
        NOT_FOUND_RESPONSE_STATUS,
        ERROR_SEVERITY);
  }

  public EntityNotFoundException(Class<?> entityClass, String s) {
    super(
        format("%s not found (%s)", entityClass.getSimpleName(), s),
        NOT_FOUND_RESPONSE_STATUS,
        ERROR_SEVERITY);
  }
}
