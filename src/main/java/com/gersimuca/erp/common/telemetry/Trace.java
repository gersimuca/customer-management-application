package com.gersimuca.erp.common.telemetry;

public enum Trace {
  CLASS_NAME("class.name"),
  METHOD_NAME("method.name"),
  CODE_FUNCTION("code.function"),
  CODE_NAMESPACE("code.namespace"),
  EXECUTION_TIME_MS("execution.time.ms");

  private final String value;

  Trace(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
