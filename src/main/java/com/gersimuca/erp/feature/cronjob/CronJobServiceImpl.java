package com.gersimuca.erp.feature.cronjob;

import com.gersimuca.erp.configuration.scheduler.config.JobType;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronJobServiceImpl implements CronJobService {
  private final CronJobRepository repository;
  private final TaskScheduler taskScheduler;
  private final CronJobMapper mapper;

  @Override
  @Transactional
  public Map<String, String> mapTimeExpressionsToTasks() {
    return repository.findAll().stream()
        .collect(Collectors.toMap(CronJobEntity::getName, CronJobEntity::getScheduledTime));
  }

  @Override
  @Transactional
  public List<CronJobDto> getTimeExpressions() {
    List<CronJobEntity> cronJobEntities =
        repository.findByNameIn(Arrays.stream(JobType.values()).map(Enum::name).toList());
    return mapper.mapToListDto(cronJobEntities);
  }

  /** schedule a one-time job */
  @Override
  public void scheduleTriggerTaskOnce(JobType jobType, Long newDelayMillis) {
    log.info("Scheduling trigger for job type {} with delay {}", jobType, newDelayMillis);
    taskScheduler.schedule(
        jobType.getRunnable(), Instant.now().plus(newDelayMillis, ChronoUnit.MILLIS));
  }
}
