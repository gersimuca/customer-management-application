package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import com.gersimuca.erp.model.ErrorCode;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.http.HttpStatus;

public class TaskRejectionException extends BaseException {
  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.SERVICE_UNAVAILABLE;
  private static final ErrorSeverity DEFAULT_ERROR_SEVERITY = ErrorSeverity.ERROR;

  public TaskRejectionException(final Runnable task, final ThreadPoolExecutor executor) {
    super(
        format("Task [%s] was rejected from executor [%s]", task.toString(), executor.toString()),
        DEFAULT_HTTP_STATUS,
        DEFAULT_ERROR_SEVERITY,
        ErrorCode.INTERNAL_ERROR);
  }

  public TaskRejectionException(
      final Runnable task,
      final ThreadPoolExecutor executor,
      final HttpStatus httpStatus,
      final ErrorSeverity errorSeverity) {
    super(
        format("Task [%s] was rejected from executor [%s]", task.toString(), executor.toString()),
        httpStatus,
        errorSeverity,
        ErrorCode.INTERNAL_ERROR);
  }
}
