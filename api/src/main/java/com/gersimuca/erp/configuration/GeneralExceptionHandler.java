package com.gersimuca.erp.configuration;

import com.gersimuca.erp.common.exception.BaseException;
import com.gersimuca.erp.common.exception.ErrorSeverity;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.model.ApiError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
      final Exception exception, final WebRequest request) {
    LoggerUtils.exception(log, exception, ErrorSeverity.WARN, request.getDescription(false));
    final HttpStatus badRequestHttpStatus = HttpStatus.BAD_REQUEST;
    final ApiError apiErrorResponse = buildApiErrorResponse(exception, badRequestHttpStatus);
    return ResponseEntity.status(badRequestHttpStatus).body(apiErrorResponse);
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

  private ApiError buildApiErrorResponse(
      final Exception exception, final HttpStatus httpResponseStatus) {
    return new ApiError()
        .status(httpResponseStatus.value())
        .code(httpResponseStatus.getReasonPhrase())
        .description(exception.getMessage());
  }
}
