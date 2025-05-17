package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.common.util.LoggerUtils;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
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
  public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
    LoggerUtils.error(
        log,
        "Task rejected. Pool size: {}, Active threads: {}, Queue size: {}, Task count: {}",
        executor.getPoolSize(),
        executor.getActiveCount(),
        executor.getQueue().size(),
        executor.getTaskCount());
    throw new TaskRejectionException(runnable, executor, httpStatus, errorSeverity);
  }
}
