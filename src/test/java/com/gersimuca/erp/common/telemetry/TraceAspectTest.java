package com.gersimuca.erp.common.telemetry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TraceAspectTest {

  @Mock private Tracer tracer;

  @Mock private Span span;

  @Mock private Scope scope;

  @Mock private ProceedingJoinPoint joinPoint;

  @Mock private Signature signature;

  @InjectMocks private TraceAspect traceAspect;

  private static final String TEST_METHOD = "testMethod";
  private static final String TEST_SERVICE = "com.test.TestService";

  @Test
  void should_trace_successful_execution() throws Throwable {
    String res = "OK";
    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getDeclaringType()).thenReturn(TestService.class);
    when(signature.getName()).thenReturn(TEST_METHOD);
    when(signature.getDeclaringTypeName()).thenReturn(TEST_SERVICE);

    SpanBuilder builder = mock(SpanBuilder.class);
    when(tracer.spanBuilder(anyString())).thenReturn(builder);
    when(builder.startSpan()).thenReturn(span);

    when(span.makeCurrent()).thenReturn(scope);
    when(joinPoint.proceed()).thenReturn(res);

    Object result = traceAspect.trace(joinPoint);

    assertEquals(res, result);

    verify(span, times(4)).setAttribute(anyString(), anyString());
    verify(span).end();
    verify(joinPoint).proceed();
  }

  @Test
  void should_handle_exception_and_mark_span_error() throws Throwable {

    when(joinPoint.getSignature()).thenReturn(signature);
    when(signature.getDeclaringType()).thenReturn(TestService.class);
    when(signature.getName()).thenReturn(TEST_METHOD);
    when(signature.getDeclaringTypeName()).thenReturn(TEST_SERVICE);
    ;

    io.opentelemetry.api.trace.SpanBuilder builder =
        mock(io.opentelemetry.api.trace.SpanBuilder.class);

    when(tracer.spanBuilder(anyString())).thenReturn(builder);
    when(builder.startSpan()).thenReturn(span);

    when(span.makeCurrent()).thenReturn(scope);

    when(joinPoint.proceed()).thenThrow(new RuntimeException("boom"));

    assertThrows(RuntimeException.class, () -> traceAspect.trace(joinPoint));

    verify(span).recordException(any(RuntimeException.class));
    verify(span).setStatus(any(), anyString());
    verify(span).end();
  }

  static class TestService {}
}
