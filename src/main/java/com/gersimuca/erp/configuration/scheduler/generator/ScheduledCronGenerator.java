package com.gersimuca.erp.configuration.scheduler.generator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class ScheduledCronGenerator {

  private Integer hours;
  private Integer minutes;
  private Integer seconds;

  public String createScheduleExpression() {
    return new StringBuilder()
        .append(seconds != null ? seconds : "*")
        .append(" ")
        .append(minutes != null ? minutes : "*")
        .append(" ")
        .append(hours != null ? hours : "*")
        .append(" ")
        .append("*")
        .append(" ")
        .append("*")
        .append(" ")
        .append("*")
        .toString();
  }
}
