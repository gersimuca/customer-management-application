package com.gersimuca.erp.configuration.async;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

class MappedDiagnosticContextTaskDecoratorTest {

  @AfterEach
  void clearMdc() {
    MDC.clear();
  }

  @Test
  void decorateCopiesAndClearsMdc() {
    MDC.put("userId", "123");
    MDC.put("sessionId", "456");
    MDC.put("requestId", "789");
    TaskDecorator decorator = new MappedDiagnosticContextTaskDecorator();
    Runnable wrapped = decorator.decorate(() -> assertEquals("123", MDC.get("userId")));
    wrapped.run();
    assertNull(MDC.get("userId"));
  }

  @Test
  void decorate_returnsOriginalRunnable_whenContextMapIsNull() {
    TaskDecorator decorator = new MappedDiagnosticContextTaskDecorator();
    Runnable originalRunnable = mock(Runnable.class);
    Runnable decoratedRunnable = decorator.decorate(originalRunnable);
    assertSame(originalRunnable, decoratedRunnable);
  }
}
