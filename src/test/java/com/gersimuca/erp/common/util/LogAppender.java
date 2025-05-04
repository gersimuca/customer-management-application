package com.gersimuca.erp.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

/** Class extending ListAppender with utility classes related to the logs */
public class LogAppender extends ListAppender<ILoggingEvent> {

  /**
   * asserts, that a given Log ListAppender contains a log entry with the expected message and level
   *
   * @param expectedLevel level expected for the log that will be searched in the list of logs
   * @param expectedMessage the message of the log that will be searched in the list of logs
   */
  public void assertThatLogContainsPartial(Level expectedLevel, String expectedMessage) {
    var matchingLogEvents =
        this.list.stream()
            .filter(logEvent -> logEvent.getFormattedMessage().contains(expectedMessage))
            .toList();
    assertThat(matchingLogEvents)
        .as("there is no log message with content '%s'", expectedMessage)
        .isNotEmpty();

    var matchingLogLevel =
        matchingLogEvents.stream()
            .filter(logEvent -> logEvent.getLevel().equals(expectedLevel))
            .findFirst();
    assertThat(matchingLogLevel)
        .as(
            "log message has been found but it does not have the expected LogLevel: %s",
            expectedLevel.toString())
        .isNotEmpty();
  }
}
