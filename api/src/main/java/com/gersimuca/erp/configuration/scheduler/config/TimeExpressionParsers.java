package com.gersimuca.erp.configuration.scheduler.config;

import com.gersimuca.erp.configuration.scheduler.paser.HhMmParser;
import com.gersimuca.erp.configuration.scheduler.paser.Iso8601Parser;
import com.gersimuca.erp.configuration.scheduler.paser.PeriodicCronExpressionParser;
import com.gersimuca.erp.configuration.scheduler.paser.ScheduledCronExpressionParser;
import com.gersimuca.erp.configuration.scheduler.paser.TimeExpressionParser;

public enum TimeExpressionParsers {
  ISO_8601(new Iso8601Parser()),
  HHMM(new HhMmParser()),
  PERIODIC_CRON(new PeriodicCronExpressionParser()),
  SCHEDULED_CRON(new ScheduledCronExpressionParser());

  public final TimeExpressionParser parser;

  TimeExpressionParsers(TimeExpressionParser parser) {
    this.parser = parser;
  }
}
