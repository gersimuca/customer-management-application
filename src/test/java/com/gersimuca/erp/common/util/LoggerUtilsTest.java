package com.gersimuca.erp.common.util;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gersimuca.erp.common.exception.ErrorSeverity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.web.context.request.WebRequest;

class LoggerUtilsTest {

  @Mock private Logger logger;
  @Mock private WebRequest request;

  private Exception exception;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(request.getDescription(false)).thenReturn("Mock request description");
    exception = new Exception("Test exception");
  }

  @Test
  void exception_logsErrorWithRequestDetails_whenSeverityIsError() {
    LoggerUtils.exception(logger, exception, ErrorSeverity.ERROR, request.getDescription(false));

    final Object[] args = {exception.getMessage(), request.getDescription(false), exception};
    verify(logger).error("Exception occurred: {}, Request Details: {}", args);
  }

  @Test
  void exception_logsInfoWithRequestDetails_whenSeverityIsInfo() {
    LoggerUtils.exception(logger, exception, ErrorSeverity.INFO, request.getDescription(false));

    final Object[] args = {exception.getMessage(), request.getDescription(false)};
    verify(logger).info("Exception occurred: {}, Request Details: {}", args);
  }

  @Test
  void exception_logsWarningWithRequestDetails_whenSeverityIsWarn() {
    LoggerUtils.exception(logger, exception, ErrorSeverity.WARN, request.getDescription(false));

    final Object[] args = {exception.getMessage(), request.getDescription(false)};
    verify(logger).warn("Exception occurred: {}, Request Details: {}", args);
  }
}
