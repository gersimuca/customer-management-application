package com.gersimuca.erp.configuration.async;

import com.gersimuca.erp.common.util.LoggerUtils;
import jakarta.annotation.PreDestroy;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncExecutorShutdown {
  private final List<ThreadPoolTaskExecutor> threadPoolTaskExecutors;

  @PreDestroy
  public void shutdown() {
    threadPoolTaskExecutors.forEach(
        executor -> {
          String prefix = executor.getThreadNamePrefix();
          LoggerUtils.info(log, "Shutting down executor with prefix: {}", prefix);
          executor.shutdown();
        });
  }
}
