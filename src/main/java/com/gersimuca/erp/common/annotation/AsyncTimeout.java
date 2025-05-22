package com.gersimuca.erp.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Async
public @interface AsyncTimeout {
  long timeout() default 5L;

  TimeUnit unit() default TimeUnit.SECONDS;
}
