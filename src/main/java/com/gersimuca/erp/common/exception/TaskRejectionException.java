package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.http.HttpStatus;

public class TaskRejectionException extends BaseException {
  private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.SERVICE_UNAVAILABLE;
  private static final ErrorSeverity DEFAULT_ERROR_SEVERITY = ErrorSeverity.ERROR;

  public TaskRejectionException(Runnable task, ThreadPoolExecutor executor) {
    super(
        format("Task [%s] was rejected from executor [%s]", task.toString(), executor.toString()),
        DEFAULT_HTTP_STATUS,
        DEFAULT_ERROR_SEVERITY);
  }

  public TaskRejectionException(
      Runnable task,
      ThreadPoolExecutor executor,
      HttpStatus httpStatus,
      ErrorSeverity errorSeverity) {
    super(
        format("Task [%s] was rejected from executor [%s]", task.toString(), executor.toString()),
        httpStatus,
        errorSeverity);
  }
}
