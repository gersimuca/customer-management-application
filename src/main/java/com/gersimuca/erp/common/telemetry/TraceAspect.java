package com.gersimuca.erp.common.telemetry;

import static com.gersimuca.erp.common.telemetry.Trace.CLASS_NAME;
import static com.gersimuca.erp.common.telemetry.Trace.CODE_FUNCTION;
import static com.gersimuca.erp.common.telemetry.Trace.CODE_NAMESPACE;
import static com.gersimuca.erp.common.telemetry.Trace.EXECUTION_TIME_MS;
import static com.gersimuca.erp.common.telemetry.Trace.METHOD_NAME;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TraceAspect {

  private final Tracer tracer;

  @Around("@within(org.springframework.stereotype.Service)")
  public Object trace(final ProceedingJoinPoint joinPoint) throws Throwable {
    final long start = System.currentTimeMillis();
    final String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
    final String methodName = joinPoint.getSignature().getName();
    final String spanName = String.format("%s.%s", className, methodName);
    final Span span = tracer.spanBuilder(spanName).startSpan();
    try (final Scope ignored = span.makeCurrent()) {
      span.setAttribute(CLASS_NAME.getValue(), className);
      span.setAttribute(METHOD_NAME.getValue(), methodName);
      span.setAttribute(CODE_NAMESPACE.getValue(), joinPoint.getSignature().getDeclaringTypeName());
      span.setAttribute(CODE_FUNCTION.getValue(), methodName);
      return joinPoint.proceed();
    } catch (final Exception exception) {
      span.recordException(exception);
      span.setStatus(StatusCode.ERROR, exception.getMessage());
      throw exception;
    } finally {
      span.setAttribute(EXECUTION_TIME_MS.getValue(), System.currentTimeMillis() - start);
      span.end();
    }
  }
}
