package com.gersimuca.erp.configuration.async;

import com.gersimuca.erp.common.util.LoggerUtils;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncConfig implements AsyncConfigurer {
  private final @Qualifier("asyncSecurityContexExecutor") Executor executor;

  @Override
  public Executor getAsyncExecutor() {
    LoggerUtils.info(log, "Using DEFAULT Async Executor: {}", executor.getClass().getSimpleName());
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (ex, method, params) ->
        LoggerUtils.error(
            log,
            "Async error occurred in method: {} with params: {} and exception: {}",
            method.getName(),
            params,
            ex);
  }
}
