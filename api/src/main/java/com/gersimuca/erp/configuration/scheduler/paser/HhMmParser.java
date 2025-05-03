package com.gersimuca.erp.configuration.scheduler.paser;

import com.gersimuca.erp.configuration.scheduler.generator.PeriodicCronGenerator;
import com.gersimuca.erp.configuration.scheduler.generator.ScheduledCronGenerator;
import java.time.Instant;
import java.time.ZoneOffset;

public class HhMmParser implements TimeExpressionParser {
  public static final String FORMAT_REGEX = "\\d\\d\\d\\d";

  @Override
  public boolean isValidExpression(String expression) {
    return expression.matches(FORMAT_REGEX);
  }

  @Override
  public Instant parse(String hhmmTimePart) {
    return parseEnriched(this.enrichHhMmFromTimePart(hhmmTimePart));
  }

  @Override
  public String parsePeriodicCron(String expression) {
    Long newDelaySeconds = parse(expression).toEpochMilli() / 1000L;
    PeriodicCronGenerator periodicCronGenerator =
        PeriodicCronGenerator.builder().seconds(newDelaySeconds).build();
    return periodicCronGenerator.createScheduleExpression();
  }

  @Override
  public String parseScheduledCron(String expression) {
    Instant parsedTime = parse(expression);
    ScheduledCronGenerator scheduledCronGenerator =
        ScheduledCronGenerator.builder()
            .hours(parsedTime.atZone(ZoneOffset.UTC).getHour())
            .minutes(parsedTime.atZone(ZoneOffset.UTC).getMinute())
            .seconds(parsedTime.atZone(ZoneOffset.UTC).getSecond())
            .build();
    return scheduledCronGenerator.createScheduleExpression();
  }

  private Instant parseEnriched(String hhmmExpression) {
    return Instant.parse(hhmmExpression);
  }

  public String enrichHhMmFromTimePart(String hhmmTimePart) {
    if (!hhmmTimePart.matches(FORMAT_REGEX)) {
      throw new IllegalArgumentException("Invalid hhmm time: " + hhmmTimePart);
    }
    return new StringBuilder()
        .append("1970-01-01T")
        .append(hhmmTimePart.toCharArray()[0])
        .append(hhmmTimePart.toCharArray()[1])
        .append(":")
        .append(hhmmTimePart.toCharArray()[2])
        .append(hhmmTimePart.toCharArray()[3])
        .append(":00.00Z")
        .toString();
  }
}
