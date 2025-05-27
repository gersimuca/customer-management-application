package com.gersimuca.erp.feature.cronjob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gersimuca.erp.configuration.scheduler.config.JobType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CronJobServiceImplTest {
  @Mock private CronJobRepository repository;
  @Mock private CronJobMapper mapper;

  @InjectMocks private CronJobServiceImpl service;

  private AutoCloseable closeable;

  @BeforeEach
  void contextLoads() {
    assertNotNull(repository, "Repository should not be null");
    assertNotNull(mapper, "Mapper should not be null");
    assertNotNull(service, "Service should not be null");
  }

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @ParameterizedTest
  @ValueSource(strings = {"Job1", "Job2", "Job3", "Job4", "Job5"})
  void mapTimeExpressionsToTasks(final String jobName) {
    List<CronJobEntity> entities = CronJobTestData.createCronJobEntities();
    when(repository.findAll()).thenReturn(entities);
    Map<String, String> result = service.mapTimeExpressionsToTasks();
    assertEquals(entities.size(), result.size());
    assertTrue(result.containsKey(jobName));
    assertEquals(
        entities.stream()
            .filter(entity -> entity.getName().equals(jobName))
            .map(CronJobEntity::getScheduledTime)
            .findFirst()
            .orElse(null),
        result.get(jobName));
    verify(repository, times(1)).findAll();
  }

  @ParameterizedTest
  @ValueSource(strings = {"Job1", "Job2", "Job3", "Job4", "Job5"})
  void getTimeExpressions(final String jobName) {
    List<CronJobEntity> cronJobEntities = CronJobTestData.createCronJobEntities();
    List<CronJobDto> cronJobDtos = CronJobTestData.createCronJobDtos();
    when(repository.findByNameIn(Arrays.stream(JobType.values()).map(Enum::name).toList()))
        .thenReturn(cronJobEntities);
    when(mapper.mapToListDto(anyList())).thenReturn(cronJobDtos);

    List<CronJobDto> result = service.getTimeExpressions();

    assertEquals(cronJobDtos.size(), result.size());
    assertTrue(result.stream().anyMatch(dto -> dto.getName().equals(jobName)));
    assertEquals(
        cronJobDtos.stream()
            .filter(dto -> dto.getName().equals(jobName))
            .map(CronJobDto::getScheduledTime)
            .findFirst()
            .orElse(null),
        result.stream()
            .filter(dto -> dto.getName().equals(jobName))
            .map(CronJobDto::getScheduledTime)
            .findFirst()
            .orElse(null));
    verify(repository, times(1))
        .findByNameIn(Arrays.stream(JobType.values()).map(Enum::name).toList());
  }
}
