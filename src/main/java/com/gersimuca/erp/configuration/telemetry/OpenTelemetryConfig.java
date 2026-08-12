package com.gersimuca.erp.configuration.telemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {

  private static final String INSTRUMENTATION_SCOPE_NAME = "erp-customer-manager";

  @Bean
  public Tracer tracer(final OpenTelemetry openTelemetry) {
    return openTelemetry.getTracer(INSTRUMENTATION_SCOPE_NAME);
  }
}
