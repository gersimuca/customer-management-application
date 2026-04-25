package com.gersimuca.erp.configuration.exception;

import com.gersimuca.erp.common.exception.BaseException;
import com.gersimuca.erp.common.exception.EncryptionException;
import com.gersimuca.erp.common.exception.ErrorSeverity;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.model.ApiError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ApiError> handleBaseException(
      final BaseException exception, final WebRequest request) {
    LoggerUtils.exception(
        log, exception, exception.getErrorSeverity(), request.getDescription(false));
    final HttpStatus errorHttpStatus = exception.getHttpResponseStatus();
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, errorHttpStatus);
    return ResponseEntity.status(errorHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError> handleAccessDeniedException(
      final AccessDeniedException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus forbiddenHttpStatus = HttpStatus.FORBIDDEN;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, forbiddenHttpStatus);
    return ResponseEntity.status(forbiddenHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleConstraintViolationException(
      final ConstraintViolationException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus badRequestHttpStatus = HttpStatus.BAD_REQUEST;

    final ApiError apiErrorResponse = buildApiErrorResponse(exception, badRequestHttpStatus);
    return ResponseEntity.status(badRequestHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(
      final MethodArgumentTypeMismatchException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus badRequestHttpStatus = HttpStatus.BAD_REQUEST;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, badRequestHttpStatus);
    return ResponseEntity.status(badRequestHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
      final Exception exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus badRequestHttpStatus = HttpStatus.BAD_REQUEST;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, badRequestHttpStatus);
    return ResponseEntity.status(badRequestHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus internalServerErrorHttpStatus = HttpStatus.BAD_REQUEST;
    final ApiError apiErrorResponse =
        buildApiErrorResponse(exception, internalServerErrorHttpStatus);
    return ResponseEntity.status(internalServerErrorHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(
      final Exception exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.ERROR, request.getDescription(false));
    final HttpStatus internalServerErrorHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    final ApiError apiErrorResponse =
        buildApiErrorResponse(exception, internalServerErrorHttpStatus);
    return ResponseEntity.status(internalServerErrorHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
  public ResponseEntity<ApiError> handleObjectOptimisticLockingFailureException(
      final ObjectOptimisticLockingFailureException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus conflictHttpStatus = HttpStatus.CONFLICT;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, conflictHttpStatus);
    return ResponseEntity.status(conflictHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(NonUniqueObjectException.class)
  public ResponseEntity<ApiError> handleNonUniqueObjectException(
      final NonUniqueObjectException exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus conflictHttpStatus = HttpStatus.CONFLICT;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, conflictHttpStatus);
    return ResponseEntity.status(conflictHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleConstraintViolationException(
      final org.hibernate.exception.ConstraintViolationException exception,
      final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus conflictHttpStatus = HttpStatus.CONFLICT;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, conflictHttpStatus);
    return ResponseEntity.status(conflictHttpStatus).body(apiErrorResponse);
  }

  @ExceptionHandler(EncryptionException.class)
  public ResponseEntity<ApiError> handleEncryptionException(
      final EncryptionException exception, final WebRequest request) {
    LoggerUtils.exception(
        log, exception, exception.getErrorSeverity(), request.getDescription(false));
    HttpStatus status = exception.getHttpResponseStatus();
    ApiError error = buildApiErrorResponse(exception, status);
    return ResponseEntity.status(status).body(error);
  }

  private ApiError buildApiErrorResponse(
      final Exception exception, final HttpStatus httpResponseStatus) {
    return new ApiError()
        .status(httpResponseStatus.value())
        .code(httpResponseStatus.getReasonPhrase())
        .description(exception.getMessage());
  }
}
