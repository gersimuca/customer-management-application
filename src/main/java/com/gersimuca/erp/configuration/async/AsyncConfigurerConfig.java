package com.gersimuca.erp.configuration.async;

import com.gersimuca.erp.common.util.LoggerUtils;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncConfigurerConfig implements AsyncConfigurer {
  private final Executor executor;

  public AsyncConfigurerConfig(@Qualifier("asyncSecurityContexExecutor") Executor executor) {
    this.executor = executor;
  }

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
