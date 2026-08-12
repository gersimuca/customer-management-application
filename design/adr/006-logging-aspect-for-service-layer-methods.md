# 006-logging-aspect-for-service-layer-methods

## Context

In the application, consistent logging of service layer methods is essential for tracking execution, debugging errors, and monitoring performance. Manually adding logging code to every service method is error-prone, verbose, and inconsistent. There is a need for a centralized, automatic logging solution that captures:

* Method start and end events.
* Execution duration.
* Exceptions thrown by service methods.
* A uniform logging format using a shared utility.

The solution must work transparently for all service methods and avoid cluttering business logic.

---

## Decision

We implemented a **LoggingAspect** using Spring AOP, which:

* Intercepts all service methods matching the pointcut `execution(* com.gersimuca.erp.feature..*Service.*(..))`.
* Logs a method start message with the class and method name before execution.
* Records start time in a thread-local variable.
* Logs a method finish message with the execution duration after completion.
* Logs any exceptions thrown with execution duration and error message.
* Uses a centralized `LoggerUtils` for consistent log formatting.
* Clears thread-local timing data after each method call to prevent memory leaks.

### Key Implementation Details

* Aspect is annotated with `@Aspect` and `@Component`.
* Uses `@Before`, `@After`, and `@AfterThrowing` advices.
* Execution time is formatted into minutes, seconds, and milliseconds.
* Ensures thread safety with `ThreadLocal<Long>` to track method start time.

---

## Alternatives Considered

| Option                                | Pros                     | Cons                                         |
| ------------------------------------- | ------------------------ | -------------------------------------------- |
| Manual logging in each service method | Simple to implement      | Inconsistent, verbose, error-prone           |
| External monitoring/APM tools         | Advanced metrics         | May not capture all required logs internally |
| Using Spring's `HandlerInterceptor`   | Good for web controllers | Does not cover service layer methods         |

---

## Consequences

* Centralized, consistent logging across all service methods.
* Automatic timing and exception logging improves observability.
* Cleaner service code without manual logging.
* Slight overhead due to thread-local time tracking and AOP proxy.
* Requires understanding of AOP and proper testing of aspect logic.

---

## Future Considerations

* Enhance with asynchronous logging to reduce impact on service performance.
* Extend aspect to cover other layers such as repositories or controllers.

