package com.gersimuca.erp.configuration.async;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AsyncConfiguration.class)
@EnableConfigurationProperties(AsyncConfiguration.class)
@TestPropertySource(
    properties = {
      "async.core-pool-size=10",
      "async.max-pool-size=20",
      "async.queue-capacity=50",
      "async.keep-alive-seconds=60",
      "async.wait-for-tasks-to-complete-on-shutdown=true",
      "async.rejected-execution-handler-policy=abort",
      "async.phase=15"
    })
class AsyncConfigurationTest {
  @Autowired ApplicationContext ctx;

  @Test
  void taskExecutorProperties() {
    ThreadPoolTaskExecutor executor =
        ctx.getBean("asyncTaskExecutor", ThreadPoolTaskExecutor.class);
    assertNotNull(executor);
    assertEquals(10, executor.getCorePoolSize());
    assertEquals(20, executor.getMaxPoolSize());
    assertEquals(50, executor.getQueueCapacity());
    assertEquals(60, executor.getKeepAliveSeconds());
    assertEquals(15, executor.getPhase()); // :contentReference[oaicite:6]{index=6}
  }

  @Test
  void securityExecutorWrapping() {
    Executor sec = ctx.getBean("asyncSecurityContexExecutor", Executor.class);
    assertInstanceOf(
        DelegatingSecurityContextExecutor.class, sec); // :contentReference[oaicite:8]{index=8}
  }

  @Test
  void decoratorActuallyWrapsTasks() throws InterruptedException {
    ThreadPoolTaskExecutor executor =
        ctx.getBean("asyncTaskExecutor", ThreadPoolTaskExecutor.class);

    String identifier = UUID.randomUUID().toString();
    MDC.put("Identifier", identifier);

    CountDownLatch latch = new CountDownLatch(1);
    AtomicReference<String> result = new AtomicReference<>();

    executor.submit(
        () -> {
          result.set(MDC.get("Identifier"));
          latch.countDown();
        });

    assertTrue(latch.await(5, TimeUnit.SECONDS));
    assertEquals(identifier, result.get());
  }
}
