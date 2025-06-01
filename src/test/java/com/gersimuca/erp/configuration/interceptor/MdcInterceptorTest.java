package com.gersimuca.erp.configuration.interceptor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.MDC;

class MdcInterceptorTest {
  @InjectMocks MdcInterceptor interceptor;
  @Mock private AutoCloseable closeable;

  @BeforeEach
  void contextLoads() {
    assertNotNull(interceptor, "Interceptor should not be null");
  }

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void testPreHandleAddRequestId() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    assertNull(MDC.get("requestId"));

    boolean result = interceptor.preHandle(request, response, new Object());

    assertTrue(result);

    String requestId = MDC.get("requestId");
    assertNotNull(requestId);

    assertTrue(requestId.matches("[0-9A-F-]{36}"));
  }

  @Test
  void testAfterCompletionRemovesRequestId() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    MDC.put("requestId", "TEST_ID");

    interceptor.afterCompletion(request, response, new Object(), null);

    assertNull(MDC.get("requestId"));
  }
}
