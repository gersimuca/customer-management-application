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

class AsyncConfigTest {

  @Test
  void defaultExecutorIsInjected() {
    Executor mockExec = mock(Executor.class);
    AsyncConfig config = new AsyncConfig(mockExec);
    assertSame(
        mockExec,
        config.getAsyncExecutor(),
        "AsyncConfig did not return the executor instance that was injected");
  }

  @Test
  void uncaughtExceptionHandlerNotNull()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = AsyncConfig.class.getMethod("getAsyncUncaughtExceptionHandler");
    assertNotNull(
        method.invoke(new AsyncConfig(mock(Executor.class))),
        "AsyncUncaughtExceptionHandler should not be null");
  }

  @Test
  void uncaughtExceptionHandler_handlesExceptionGracefully() throws NoSuchMethodException {
    Executor mockExecutor = mock(Executor.class);
    AsyncConfig config = new AsyncConfig(mockExecutor);

    Method method = DummyService.class.getMethod("failingAsyncMethod", String.class);
    Object[] params = {"sample-input"};
    Throwable simulatedException = new RuntimeException("Simulated async error");

    AsyncUncaughtExceptionHandler handler = config.getAsyncUncaughtExceptionHandler();
    assertNotNull(handler, "AsyncUncaughtExceptionHandler should not be null");

    assertDoesNotThrow(() -> handler.handleUncaughtException(simulatedException, method, params));
  }

  // Fake class to simulate a target method for testing
  static class DummyService {
    public void failingAsyncMethod(String input) {
      // no-op; just for reflection test
    }
  }
}
