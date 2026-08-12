package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {
  public EntityAlreadyExistsException(final Class<?> entityClass, final Long id) {
    super(
        format("%s with id %d already exists", entityClass.getSimpleName(), id),
        HttpStatus.CONFLICT,
        ErrorSeverity.WARN,
        ErrorCode.DUPLICATE_RESOURCE);
  }

  public EntityAlreadyExistsException(final Class<?> entityClass, final String property) {
    super(
        format("%s already exists (%s)", entityClass, property),
        HttpStatus.CONFLICT,
        ErrorSeverity.WARN,
        ErrorCode.DUPLICATE_RESOURCE);
  }
}
