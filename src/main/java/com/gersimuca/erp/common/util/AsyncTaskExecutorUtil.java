package com.gersimuca.erp.common.util;

import com.gersimuca.erp.common.exception.TaskExecutionTimeoutException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AsyncTaskExecutorUtil {
  private ThreadPoolTaskExecutor asyncTaskExecutor;

  public void executeWithTimeout(Runnable task, long timeout, TimeUnit timeUnit) {
    try {
      CompletableFuture.runAsync(task, asyncTaskExecutor)
          .orTimeout(timeout, timeUnit)
          .exceptionally(
              ex -> {
                throw new TaskExecutionTimeoutException(
                    task.getClass(), "Runnable Task", timeout, timeUnit.name());
              })
          .join();
    } catch (CompletionException ex) {
      throw ex.getCause() instanceof RuntimeException
          ? (RuntimeException) ex.getCause()
          : new RuntimeException(ex);
    }
  }

  public <T> T executeWithTimeout(Supplier<T> task, long timeout, TimeUnit timeUnit) {
    try {
      return CompletableFuture.supplyAsync(task, asyncTaskExecutor)
          .orTimeout(timeout, timeUnit)
          .exceptionally(
              ex -> {
                throw new TaskExecutionTimeoutException(
                    task.getClass(), "Supplier Task", timeout, timeUnit.name());
              })
          .join();
    } catch (CompletionException ex) {
      Throwable cause = ex.getCause();
      if (cause instanceof RuntimeException runtimeException) {
        throw runtimeException;
      } else {
        throw new RuntimeException(ex);
      }
    }
  }
}
