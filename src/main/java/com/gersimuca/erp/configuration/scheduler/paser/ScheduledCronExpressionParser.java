package com.gersimuca.erp.configuration.scheduler.paser;

import java.time.Instant;
import org.springframework.scheduling.support.CronExpression;

public class ScheduledCronExpressionParser implements TimeExpressionParser {
  @Override
  public boolean isValidExpression(String expression) {
    return CronExpression.isValidExpression(expression)
        && !"* * * * * *".equals(expression)
        && !expression.contains("/");
  }

  @Override
  public Instant parse(String cronExpression) {
    throw new IllegalArgumentException(
        "Generating an Instance from a CronExpression is not implemented!");
  }

  @Override
  public String parsePeriodicCron(String expression) {
    throw new IllegalArgumentException(
        "ScheduledCronExpressionParser can not parse periodic cron!");
  }

  @Override
  public String parseScheduledCron(String expression) {
    return expression;
  }
}
