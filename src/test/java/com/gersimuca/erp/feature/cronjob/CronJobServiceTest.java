package com.gersimuca.erp.feature.cronjob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.TaskScheduler;

class CronJobServiceTest {
  @Mock private CronJobRepository repository;
  @Mock private CronJobMapper mapper;
  @Mock private TaskScheduler taskScheduler;

  @InjectMocks private CronJobService service;

  private AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void mapTimeExpressionsToTasks() {
    List<CronJobEntity> entities = CronJobTestData.createCronJobEntities();

    when(repository.findAll()).thenReturn(entities);

    Map<String, String> result = service.mapTimeExpressionsToTasks();

    assertEquals(entities.size(), result.size());
    assertEquals("0 * * * *", result.get("Job1"));
    assertEquals("0 * * * *", result.get("Job2"));
    verify(repository).findAll();
  }
}
