package com.gersimuca.erp.feature.cronjob;

import java.util.List;

class CronJobTestData {
  public static CronJobEntity createCronJobEntity(
      final String cronJobName, final String scheduledTime) {
    return CronJobEntity.builder().scheduledTime(scheduledTime).name(cronJobName).build();
  }

  public static List<CronJobEntity> createCronJobEntities() {
    return List.of(
        createCronJobEntity("Job1", "0 1 * * *"),
        createCronJobEntity("Job2", "0 2 * * *"),
        createCronJobEntity("Job3", "0 3 * * *"),
        createCronJobEntity("Job4", "0 4 * * *"),
        createCronJobEntity("Job5", "0 5 * * *"));
  }

  public static CronJobDto createCronJobDto(final String cronJobName, final String scheduledTime) {
    return CronJobDto.builder().scheduledTime(scheduledTime).name(cronJobName).build();
  }

  public static List<CronJobDto> createCronJobDtos() {
    return List.of(
        createCronJobDto("Job1", "0 1 * * *"),
        createCronJobDto("Job2", "0 2 * * *"),
        createCronJobDto("Job3", "0 3 * * *"),
        createCronJobDto("Job4", "0 4 * * *"),
        createCronJobDto("Job5", "0 5 * * *"));
  }
}
