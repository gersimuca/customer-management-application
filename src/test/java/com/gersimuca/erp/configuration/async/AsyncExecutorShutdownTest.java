package com.gersimuca.erp.configuration.async;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

class AsyncExecutorShutdownTest {
  @Test
  void shutdownAllExecutors() {
    ThreadPoolTaskExecutor exec1 = Mockito.mock(ThreadPoolTaskExecutor.class);
    ThreadPoolTaskExecutor exec2 = Mockito.mock(ThreadPoolTaskExecutor.class);
    AsyncExecutorShutdown shutdown = new AsyncExecutorShutdown(List.of(exec1, exec2));

    shutdown.shutdown();

    Mockito.verify(exec1).shutdown();
    Mockito.verify(exec2).shutdown();
  }
}
