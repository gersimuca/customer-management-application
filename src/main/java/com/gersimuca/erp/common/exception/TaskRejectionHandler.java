package com.gersimuca.erp.common.exception;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.http.HttpStatus;

public class TaskRejectionHandler implements RejectedExecutionHandler {
  private final HttpStatus httpStatus;
  private final ErrorSeverity errorSeverity;

  public TaskRejectionHandler() {
    this(HttpStatus.SERVICE_UNAVAILABLE, ErrorSeverity.ERROR);
  }

  public TaskRejectionHandler(HttpStatus httpStatus, ErrorSeverity errorSeverity) {
    this.httpStatus = httpStatus;
    this.errorSeverity = errorSeverity;
  }

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    throw new TaskRejectionException(r, executor, httpStatus, errorSeverity);
  }
}
