package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

  public EntityNotFoundException(final Class<?> entityClass, final Long id) {
    super(
        format("%s with id %d not found", entityClass.getSimpleName(), id),
        STATUS,
        ErrorSeverity.WARN,
        ErrorCode.RESOURCE_NOT_FOUND);
  }

  public EntityNotFoundException(final Class<?> entityClass, final String s) {
    super(
        format("%s not found (%s)", entityClass.getSimpleName(), s),
        STATUS,
        ErrorSeverity.WARN,
        ErrorCode.RESOURCE_NOT_FOUND);
  }
}
