package com.gersimuca.erp.configuration.scheduler.paser;

import java.time.Instant;

public interface TimeExpressionParser {

  boolean isValidExpression(String expression);

  Instant parse(String expression);

  String parseScheduledCron(String expression);

  String parsePeriodicCron(String expression);
}
