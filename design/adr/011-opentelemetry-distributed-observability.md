# 011-opentelemetry-distributed-observability

## Context

The application currently relies on traditional logging and basic metrics for observability. While sufficient for simple debugging, it does not provide:

- End-to-end tracing across distributed components
- Visibility into service-to-service latency
- Correlation between logs, metrics, and execution flows
- Standardized observability across modern cloud-native infrastructure

As the system grows (async processing, Feign clients, external APIs), diagnosing production issues becomes increasingly difficult without structured tracing.

A unified observability standard is required.

## Decision

We adopt **OpenTelemetry (OTEL)** as the standard observability framework.

### Core components

- `opentelemetry-spring-boot-starter` for auto-instrumentation
- `opentelemetry-exporter-otlp` for telemetry export
- OpenTelemetry Java Agent for runtime instrumentation

### Export configuration

- Protocol: OTLP (gRPC)
- Endpoint: http://localhost:4317
- Enabled signals:
    - Traces
    - Metrics
    - Logs

### Service configuration

- Service name: erp-customer-manager
- Propagation:
    - tracecontext
    - baggage

### Custom instrumentation

A custom AOP-based tracing layer (`TraceAspect`) is used to:

- Trace all `@Service` methods
- Create spans per execution
- Attach metadata:
    - class name
    - method name
    - namespace
    - execution time
- Capture exceptions and mark spans as ERROR

This complements auto-instrumentation from the Java agent.

## Consequences

### Positive

- Full distributed tracing across services
- Standardized OTLP observability format
- Improved debugging of latency and failures
- Reduced reliance on log-only troubleshooting
- Better correlation between services, DB, and external APIs
- Compatible with Grafana, Jaeger, Tempo, and other backends

### Negative

- Slight runtime overhead
- Requires OTLP collector infrastructure
- Potential duplicate spans (agent + AOP)
- Additional configuration complexity

## Alternatives Considered

### Logging-only (ELK / Logback)
- Rejected: no distributed tracing

### Zipkin
- Rejected: less flexible, not OpenTelemetry-native

### Metrics-only (Micrometer)
- Rejected: no request-level trace visibility

### Commercial APM (Datadog, New Relic)
- Rejected: vendor lock-in and cost

## Future Considerations

- Evaluate removal of custom AOP tracing in favor of full Java agent instrumentation
- Introduce OpenTelemetry Collector in Kubernetes for centralized processing
- Add trace sampling strategies to reduce overhead in production
- Integrate logs with trace IDs for full log-trace correlation
- Extend observability to frontend (browser tracing via OTEL JS SDK)
- Consider multi-environment separation (dev/staging/prod exporters)
- Optimize span naming conventions for cross-service consistency

## References

- https://opentelemetry.io/
- https://github.com/open-telemetry/opentelemetry-java-instrumentation
- https://github.com/open-telemetry/opentelemetry-java