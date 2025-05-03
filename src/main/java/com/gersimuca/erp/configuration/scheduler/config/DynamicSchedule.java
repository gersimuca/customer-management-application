package com.gersimuca.erp.configuration.scheduler.config;

public interface DynamicSchedule {
  void restart();

  void cron(String cronExpression);

  void delay(Long milliseconds);

  void decreaseDelayInterval(Long milliseconds);

  void increaseDelayInterval(Long milliseconds);
}
