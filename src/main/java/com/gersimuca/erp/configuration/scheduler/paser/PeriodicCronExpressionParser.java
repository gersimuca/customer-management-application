package com.gersimuca.erp.configuration.scheduler.paser;

import java.time.Instant;
import org.springframework.scheduling.support.CronExpression;

public class PeriodicCronExpressionParser implements TimeExpressionParser {

  @Override
  public boolean isValidExpression(String expression) {
    return CronExpression.isValidExpression(expression)
        && (expression.contains("/") || "* * * * * *".equals(expression));
  }

  @Override
  public Instant parse(String cronExpression) {
    throw new IllegalArgumentException(
        "Generating an Instance from a CronExpression is not implemented!");
  }

  @Override
  public String parsePeriodicCron(String expression) {
    return expression;
  }

  @Override
  public String parseScheduledCron(String expression) {
    throw new IllegalArgumentException(
        "PeriodicCronExpressionParser can not parse scheduled cron!");
  }
}
