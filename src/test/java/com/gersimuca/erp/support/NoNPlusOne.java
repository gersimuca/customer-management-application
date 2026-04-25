package com.gersimuca.erp.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(NoNPlusOneExtension.class)
public @interface NoNPlusOne {

  /** Max allowed SELECT queries */
  int selects() default 1;

  /** Max allowed total SQL statements */
  int total() default Integer.MAX_VALUE;
}
