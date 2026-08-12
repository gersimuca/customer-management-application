package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import com.gersimuca.erp.model.ErrorCode;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.http.HttpStatus;

public class TaskRejectionException extends BaseException {
  public TaskRejectionException(final Runnable task, final ThreadPoolExecutor executor) {
    super(
        format("Task [%s] was rejected from executor [%s]", task.toString(), executor.toString()),
        HttpStatus.SERVICE_UNAVAILABLE,
        ErrorSeverity.ERROR,
        ErrorCode.THREAD_POOL_REJECTED);
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
        ErrorCode.THREAD_POOL_REJECTED);
  }
}
