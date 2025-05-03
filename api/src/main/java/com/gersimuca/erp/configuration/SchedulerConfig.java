package com.gersimuca.erp.configuration;

import com.gersimuca.erp.configuration.scheduler.config.DynamicScheduledTask;
import com.gersimuca.erp.configuration.scheduler.config.JobType;
import com.gersimuca.erp.configuration.scheduler.config.ScheduledType;
import com.gersimuca.erp.configuration.scheduler.config.TimeExpressionParsers;
import com.gersimuca.erp.configuration.scheduler.paser.TimeExpressionParser;
import com.gersimuca.erp.feature.cronjob.CronJobService;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig implements SchedulingConfigurer {

  private final CronJobService service;
  // map for accessing the job triggers
  private final Map<JobType, DynamicScheduledTask> triggerMap = new HashMap<>();
  // list to iterate over job types
  private final Collection<JobType> jobTypes = Arrays.stream(JobType.values()).toList();

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.initialize();
    taskScheduler.setThreadNamePrefix("scheduled-task-pool-");
    taskScheduler.setThreadGroupName("scheduler-service-");
    taskRegistrar.setTaskScheduler(taskScheduler);

    Map<String, String> delayMapForTasks = service.mapTimeExpressionsToTasks();

    triggerMap.putAll(
        jobTypes.stream()
            .filter(
                jt ->
                    jt.isRepeatable()
                        && (jt.getScheduleType() == ScheduledType.PERIODIC
                            || jt.getScheduleType() == ScheduledType.SCHEDULED))
            .collect(
                Collectors.toMap(
                    jobType -> jobType,
                    jobType -> {
                      DynamicScheduledTask trigger =
                          new DynamicScheduledTask(taskScheduler).runnable(jobType.getRunnable());
                      String timeExpression = delayMapForTasks.get(jobType.getConfigName());
                      // find an appropriate parser for the time expression
                      TimeExpressionParser parser =
                          Arrays.stream(TimeExpressionParsers.values())
                              .filter(p -> p.parser.isValidExpression(timeExpression))
                              .findFirst()
                              .get()
                              .parser;
                      String cronExpression;
                      if (jobType.getScheduleType() == ScheduledType.PERIODIC) {
                        // schedule periodic jobs
                        cronExpression =
                            parser.parsePeriodicCron(delayMapForTasks.get(jobType.getTimerName()));
                      } else {
                        // schedule cron jobs
                        cronExpression =
                            parser.parseScheduledCron(delayMapForTasks.get(jobType.getTimerName()));
                      }
                      trigger.cron(cronExpression);
                      return trigger;
                    })));
  }

  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
  void updateTriggerTasks() {
    Map<String, String> delayMapForTasks = service.mapTimeExpressionsToTasks();
    triggerMap.forEach(
        (jobType, trigger) -> {
          // @Todo code repetition
          if (jobType.isRepeatable()
              && (jobType.getScheduleType() == ScheduledType.PERIODIC
                  || jobType.getScheduleType() == ScheduledType.SCHEDULED)) {
            String timeExpression = delayMapForTasks.get(jobType.getTimerName());
            // find an appropriate parser for the time expression
            TimeExpressionParser parser =
                Arrays.stream(TimeExpressionParsers.values())
                    .filter(p -> p.parser.isValidExpression(timeExpression))
                    .findFirst()
                    .get()
                    .parser;
            String cronExpression;
            if (jobType.getScheduleType() == ScheduledType.PERIODIC) {
              // schedule periodic jobs
              cronExpression =
                  parser.parsePeriodicCron(delayMapForTasks.get(jobType.getTimerName()));
            } else {
              // schedule cron jobs
              cronExpression =
                  parser.parseScheduledCron(delayMapForTasks.get(jobType.getTimerName()));
            }
            trigger.cron(cronExpression);
          }
        });
  }
}
