package com.gersimuca.erp.configuration.async;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;

public class MappedDiagnosticContextTaskDecorator implements TaskDecorator {
  @Override
  public Runnable decorate(@NonNull Runnable runnable) {
    Map<String, String> contextMap = MDC.getCopyOfContextMap();
    if (contextMap == null) {
      return runnable;
    }

    return () -> withContext(contextMap, runnable);
  }

  private void withContext(Map<String, String> contextMap, Runnable runnable) {
    MDC.setContextMap(contextMap);
    try {
      runnable.run();
    } finally {
      MDC.clear();
    }
  }
}
