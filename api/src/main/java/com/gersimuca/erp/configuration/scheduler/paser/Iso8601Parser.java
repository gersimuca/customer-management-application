package com.gersimuca.erp.configuration.scheduler.paser;

import com.gersimuca.erp.configuration.scheduler.generator.PeriodicCronGenerator;
import com.gersimuca.erp.configuration.scheduler.generator.ScheduledCronGenerator;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Iso8601Parser implements TimeExpressionParser {
  private static final Logger LOGGER = Logger.getLogger(Iso8601Parser.class.getName());
  public static final String FORMAT_REGEX = "\\d\\d:\\d\\d:\\d\\d";

  @Override
  public boolean isValidExpression(String expression) {
    return expression.matches(FORMAT_REGEX);
  }

  @Override
  public Instant parse(String iso8601TimePart) {
    String enrichedTimePart = this.enrichIso8601FromTimePart(iso8601TimePart);
    return parseEnriched(enrichedTimePart);
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

  private Instant parseEnriched(String iso8601Expression) {
    return Instant.parse(iso8601Expression);
  }

  public String enrichIso8601FromTimePart(String iso8601TimePart) {
    if (!iso8601TimePart.matches(FORMAT_REGEX)) {
      throw new IllegalArgumentException("Invalid iso8601 time: " + iso8601TimePart);
    }
    return "1970-01-01T" + iso8601TimePart + ".00Z";
  }
}
