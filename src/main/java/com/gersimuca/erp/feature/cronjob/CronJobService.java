package com.gersimuca.erp.feature.cronjob;

import com.gersimuca.erp.configuration.scheduler.config.JobType;
import java.util.List;
import java.util.Map;

public interface CronJobService {
  Map<String, String> mapTimeExpressionsToTasks();

  List<CronJobDto> getTimeExpressions();

  void scheduleTriggerTaskOnce(final JobType jobType, final Long newDelayMillis);
}
