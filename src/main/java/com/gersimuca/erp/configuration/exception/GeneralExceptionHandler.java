package com.gersimuca.erp.configuration.exception;

import com.gersimuca.erp.common.exception.*;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.model.ErrorCode;
import com.gersimuca.erp.model.ProblemDetail;
import com.gersimuca.erp.model.ProblemValidationError;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
  // BASE EXCEPTION
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ProblemDetail> handleBaseException(
      final BaseException ex, final WebRequest request) {
    LoggerUtils.exception(log, ex, ex.getErrorSeverity(), request.getDescription(false));
    return build(ex, ex.getHttpResponseStatus(), ex.getErrorCode(), request, List.of());
  }

  // AUTHORIZATION
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ProblemDetail> handleAccessDenied(
      final Exception ex, final WebRequest request) {
    return build(ex, HttpStatus.FORBIDDEN, ErrorCode.ACCESS_DENIED, request, List.of());
  }

  // VALIDATION (Bean validation)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleValidation(
      MethodArgumentNotValidException ex, WebRequest request) {

    final List<ProblemValidationError> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(
                err ->
                    new ProblemValidationError()
                        .field(err.getField())
                        .issue(err.getDefaultMessage())
                        .rejectedValue(err.getRejectedValue()))
            .toList();

    return build(ex, HttpStatus.BAD_REQUEST, ErrorCode.VALIDATION_FAILED, request, errors);
  }

  // CONSTRAINT VIOLATIONS
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ProblemDetail> handleConstraintViolation(
      final ConstraintViolationException ex, final WebRequest request) {

    final List<ProblemValidationError> errors =
        ex.getConstraintViolations().stream()
            .map(
                v ->
                    new ProblemValidationError()
                        .field(v.getPropertyPath().toString())
                        .issue(v.getMessage())
                        .rejectedValue(v.getInvalidValue()))
            .toList();

    return build(ex, HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REQUEST, request, errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ProblemDetail> handleBadJson(final Exception ex, final WebRequest request) {
    return build(
        ex, HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REQUEST, request, new LinkedList<>());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ProblemDetail> handleTypeMismatch(
      final Exception ex, final WebRequest request) {
    return build(
        ex, HttpStatus.BAD_REQUEST, ErrorCode.MALFORMED_REQUEST, request, new LinkedList<>());
  }

  // CONCURRENCY
  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<ProblemDetail> handleOptimisticLock(
      final Exception ex, final WebRequest request) {
    return build(
        ex, HttpStatus.CONFLICT, ErrorCode.OPTIMISTIC_LOCK_CONFLICT, request, new LinkedList<>());
  }

  // DUPLICATE / DB CONFLICTS
  @ExceptionHandler(NonUniqueObjectException.class)
  public ResponseEntity<ProblemDetail> handleNonUnique(
      final Exception ex, final WebRequest request) {
    return build(ex, HttpStatus.CONFLICT, ErrorCode.RESOURCE_CONFLICT, request, new LinkedList<>());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ProblemDetail> handleDuplicateKey(
      final Exception ex, final WebRequest request) {

    return build(ex, HttpStatus.CONFLICT, ErrorCode.DUPLICATE_RESOURCE, request, List.of());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleGeneric(final Exception ex, final WebRequest request) {
    LoggerUtils.error(log, "Unhandled exception", ex);
    return build(
        ex,
        HttpStatus.INTERNAL_SERVER_ERROR,
        ErrorCode.INTERNAL_ERROR,
        request,
        new LinkedList<>());
  }

  private ResponseEntity<ProblemDetail> build(
      final Exception ex,
      final HttpStatus status,
      final ErrorCode code,
      final WebRequest request,
      final List<ProblemValidationError> errors) {

    final ProblemDetail problem = new ProblemDetail();

    problem.setStatus(status.value());
    problem.setType(ProblemTypes.of(code));
    problem.setTitle(status.getReasonPhrase());
    problem.setDetail(ex.getMessage());

    final String path = ((ServletWebRequest) request).getRequest().getRequestURI();

    problem.setInstance(URI.create(path));
    problem.setCode(code);
    problem.setTraceId(MDC.get("traceId"));
    problem.setTimestamp(OffsetDateTime.now());
    problem.setErrors(errors);

    return ResponseEntity.status(status).body(problem);
  }
}
