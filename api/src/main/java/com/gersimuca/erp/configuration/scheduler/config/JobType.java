package com.gersimuca.erp.configuration.scheduler.config;

import com.gersimuca.erp.configuration.scheduler.job.Job;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum JobType {
  MAINTENANCE("MAINTENANCE", "MAINTENANCE", true, ScheduledType.SCHEDULED);

  // the runnable for this JobType
  @Setter private Job runnable;
  // database config name
  private final String configName;
  // timer name for console output
  private final String timerName;
  // if true, this job will be scheduled via the scheduler. If false, this job must be scheduled
  // once in the code where triggered
  private final boolean repeatable;
  // defines whether this job is scheduled periodically (e.g. all 5 seconds) of by schedule (e.g.
  // every day at 12:10)
  private final ScheduledType scheduleType;

  /**
   * constructor
   *
   * @param configName database config name
   * @param timerName timer name for console output
   * @param repeatable if true, this job will be scheduled via the scheduler. If false, this job
   *     must be scheduled once in the code where triggered
   * @param scheduleType defines whether this job is scheduled periodically (e.g. all 5 seconds) of
   *     by schedule (e.g. every day at 12:10)
   */
  JobType(String configName, String timerName, boolean repeatable, ScheduledType scheduleType) {
    this.configName = configName;
    this.timerName = timerName;
    this.repeatable = repeatable;
    this.scheduleType = scheduleType;
  }
}
