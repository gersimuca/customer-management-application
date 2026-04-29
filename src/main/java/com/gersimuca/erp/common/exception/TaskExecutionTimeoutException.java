package com.gersimuca.erp.common.exception;

import static java.lang.String.format;

import com.gersimuca.erp.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class TaskExecutionTimeoutException extends BaseException {
  private final String taskName;
  private final Long timeout;
  private final String timeUnit;
  private static final HttpStatus RESPONSE_STATUS = HttpStatus.REQUEST_TIMEOUT;
  private static final ErrorSeverity ERROR_SEVERITY = ErrorSeverity.ERROR;

  public TaskExecutionTimeoutException(
      final Class<?> serviceClass, final String taskName, final Long timeout, String timeUnit) {
    super(
        format(
            "Task '%s' in service '%s' exceeded timeout of %d %s",
            taskName, serviceClass.getSimpleName(), timeout, timeUnit),
        RESPONSE_STATUS,
        ERROR_SEVERITY,
        ErrorCode.INTERNAL_ERROR);
    this.taskName = taskName;
    this.timeout = timeout;
    this.timeUnit = timeUnit;
  }
}
