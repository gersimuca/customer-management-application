package com.gersimuca.erp.configuration.scheduler.config;

import com.gersimuca.erp.common.util.LoggerUtils;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DynamicScheduledTask implements DynamicSchedule, Trigger {

  private final TaskScheduler taskScheduler;

  private ScheduledFuture<?> scheduledFuture;

  private Runnable runnable;

  private Long delayInterval;

  private String cronExpression;

  /** Constructor * */
  public DynamicScheduledTask(TaskScheduler taskScheduler) {
    this.taskScheduler = taskScheduler;
  }

  /** builder method for defining runnable * */
  public DynamicScheduledTask runnable(Runnable runnable) {
    this.runnable = runnable;
    return this;
  }

  @Override
  public void restart() {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(false);
    }
    scheduledFuture = taskScheduler.schedule(runnable, this);
  }

  @Override
  public void cron(String cronExpression) {
    if ((cronExpression != null && !cronExpression.equals(this.cronExpression))
        || this.delayInterval > 0) {
      log.info(
          "restarting task. Old Cron: {}, New Cron: {}. Will delete DelayInterval {} ms. ",
          this.cronExpression,
          cronExpression,
          this.delayInterval);

      if (scheduledFuture != null) {
        scheduledFuture.cancel(true);
      }
      this.delayInterval = -1L;
      this.cronExpression = cronExpression;
      scheduledFuture = taskScheduler.schedule(runnable, new CronTrigger(cronExpression));
    }
  }

  @Override
  public void delay(Long milliseconds) {
    if (!Objects.equals(milliseconds, this.delayInterval) || cronExpression != null) {
      LoggerUtils.info(
          log,
          "restarting task. Old Delay: {}, New Delay: {}. Will delete cronExpression {}.",
          delayInterval,
          milliseconds,
          cronExpression);

      if (scheduledFuture != null) {
        scheduledFuture.cancel(true);
      }
      this.delayInterval = milliseconds;
      this.cronExpression = null;
      scheduledFuture = taskScheduler.schedule(runnable, this);
    }
  }

  @Override
  public void decreaseDelayInterval(Long milliseconds) {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
    }
    this.delayInterval -= milliseconds;
    scheduledFuture = taskScheduler.schedule(runnable, this);
  }

  @Override
  public void increaseDelayInterval(Long milliseconds) {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
    }
    this.delayInterval += milliseconds;
    scheduledFuture = taskScheduler.schedule(runnable, this);
  }

  @Override
  public Instant nextExecution(TriggerContext triggerContext) {
    Instant lastTime = triggerContext.lastActualExecution();

    if (lastTime == null) {
      return Instant.now();
    } else if (this.delayInterval > 0) {
      return lastTime.plus(this.delayInterval, ChronoUnit.MILLIS);
    } else {
      return CronExpression.parse(this.cronExpression).next(ZonedDateTime.now().toInstant());
    }
  }
}
