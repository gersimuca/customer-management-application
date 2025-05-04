package com.gersimuca.erp.feature.cronjob;

import java.util.List;

class CronJobTestData {
  public static CronJobEntity createCronJobEntity(final String cronJobName) {
    return CronJobEntity.builder().scheduledTime("0 * * * *").name(cronJobName).build();
  }

  public static List<CronJobEntity> createCronJobEntities() {
    return List.of(
        createCronJobEntity("Job1"),
        createCronJobEntity("Job2"),
        createCronJobEntity("Job3"),
        createCronJobEntity("Job4"),
        createCronJobEntity("Job5"));
  }
}
