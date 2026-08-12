# 005-centralized-asynchronous-execution-configuration

## Context

Modern Spring Boot applications frequently require asynchronous processing to handle long-running tasks, improve throughput, and avoid blocking the main thread. To ensure this is done consistently, transparently, and securely, we need a centralized and configurable way to manage asynchronous execution.

Key technical requirements:

* Thread pool configuration must be externalizable via configuration files.
* Async methods should propagate the Spring Security context.
* MDC (Mapped Diagnostic Context) support must be included for structured logging.
* Graceful shutdown of all thread pools must be handled during app shutdown.
* Rejection policies should be configurable and extensible.

---

## Decision

We introduced a **centralized async execution strategy** using Spring's `@EnableAsync` and a custom configuration structure with the following components:

### 1. **`AsyncConfiguration`**

* Declares two beans:

    * `asyncTaskExecutor`: Main executor (MDC + thread pool).
    * `asyncSecurityContexExecutor`: Wraps the main executor in a `DelegatingSecurityContextExecutor` for security context propagation.
* Loads pool properties from the `application.yml` (`async.*` prefix).
* Applies a `MappedDiagnosticContextTaskDecorator` for MDC propagation.
* Supports custom `RejectedExecutionHandler` via a string-based policy name.

### 2. **`AsyncConfigurerConfig`**

* Implements Spring's `AsyncConfigurer` to:

    * Supply the global default async executor.
    * Handle uncaught exceptions with structured logging.

### 3. **`AsyncExecutorShutdown`**

* Ensures all configured `ThreadPoolTaskExecutor` beans are gracefully shut down at application exit (`@PreDestroy`).

### 4. **`MappedDiagnosticContextTaskDecorator`**

* Captures the MDC context at task submission time and restores it at execution time.

### 5. **`RejectionPolicy`**

* Enum-like class that maps string values to known `RejectedExecutionHandler` implementations.
* Falls back to a `ThreadPoolExecutor.AbortPolicy` on unrecognized input.

---

## Alternatives Considered

| Option                                        | Pros                    | Cons                                                        |
| --------------------------------------------- | ----------------------- | ----------------------------------------------------------- |
| Use `SimpleAsyncTaskExecutor`                 | Zero config, easy setup | No pooling or rejection control, no MDC or security support |
| Manually configure all executors per use case | Flexible                | Boilerplate, easy to misconfigure, inconsistent behavior    |
| Delegate MDC/security manually in tasks       | Explicit                | Repetitive and error-prone                                  |

---

## Consequences

* Asynchronous behavior is **configurable**, **testable**, and **consistent** across the codebase.
* MDC context and security context are preserved in background threads.
* Custom rejection policies can be introduced and extended.
* Thread pools shut down gracefully, avoiding task loss during shutdown.
* Slightly more complex configuration structure, but mitigated by centralization and encapsulation.

---

## Future Considerations

* Consider adding a **metrics integration** (e.g., Micrometer) to monitor task execution stats and thread pool usage.
* Allow **dynamic updates** of executor settings if runtime reconfiguration is needed.