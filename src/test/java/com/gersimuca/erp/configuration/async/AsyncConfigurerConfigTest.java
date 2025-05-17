package com.gersimuca.erp.configuration.async;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.junit.jupiter.api.Test;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

class AsyncConfigurerConfigTest {

  @Test
  void defaultExecutorIsInjected() {
    Executor mockExec = mock(Executor.class);
    AsyncConfigurerConfig config = new AsyncConfigurerConfig(mockExec);
    assertSame(mockExec, config.getAsyncExecutor());
  }

  @Test
  void uncaughtExceptionHandlerNotNull()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = AsyncConfigurerConfig.class.getMethod("getAsyncUncaughtExceptionHandler");
    assertNotNull(method.invoke(new AsyncConfigurerConfig(mock(Executor.class))));
  }

  @Test
  void uncaughtExceptionHandler_handlesExceptionGracefully() throws NoSuchMethodException {
    Executor mockExecutor = mock(Executor.class);
    AsyncConfigurerConfig config = new AsyncConfigurerConfig(mockExecutor);

    Method method = DummyService.class.getMethod("failingAsyncMethod", String.class);
    Object[] params = {"sample-input"};
    Throwable simulatedException = new RuntimeException("Simulated async error");

    AsyncUncaughtExceptionHandler handler = config.getAsyncUncaughtExceptionHandler();
    assertNotNull(handler, "Handler should not be null");

    assertDoesNotThrow(() -> handler.handleUncaughtException(simulatedException, method, params));
  }

  // Fake class to simulate a target method for testing
  static class DummyService {
    public void failingAsyncMethod(String input) {}
  }
}
