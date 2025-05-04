package com.gersimuca.erp.common.util;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/** Testing utilities class */
public class TestUtils {
  /**
   * Method used to configure the logs observer for a specified class
   *
   * @param observedClass class whose logs will be observed
   * @return LogAppender for that class containing information about the logs
   */
  public static LogAppender createLogAppenderForClass(Class<?> observedClass) {
    Logger classLogger = (Logger) LoggerFactory.getLogger(observedClass);

    LogAppender logEvents = new LogAppender();
    logEvents.start();

    classLogger.addAppender(logEvents);

    return logEvents;
  }
}
