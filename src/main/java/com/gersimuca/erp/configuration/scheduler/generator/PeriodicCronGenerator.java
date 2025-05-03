package com.gersimuca.erp.configuration.scheduler.generator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class PeriodicCronGenerator {

  private Long hours;
  private Long minutes;
  private Long seconds;

  public String createScheduleExpression() {
    return new StringBuilder()
        .append("*")
        .append(seconds != null && seconds > 1 ? "/" : "")
        .append(seconds != null && seconds > 1 ? seconds : "")
        .append(" ")
        .append("*")
        .append(minutes != null && minutes > 1 ? "/" : "")
        .append(minutes != null && minutes > 1 ? minutes : "")
        .append(" ")
        .append("*")
        .append(hours != null && hours > 1 ? "/" : "")
        .append(hours != null && hours > 1 ? hours : "")
        .append(" ")
        .append("*")
        .append(" ")
        .append("*")
        .append(" ")
        .append("*")
        .toString();
  }
}
