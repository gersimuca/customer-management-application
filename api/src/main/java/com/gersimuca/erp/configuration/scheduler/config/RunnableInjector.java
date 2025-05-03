package com.gersimuca.erp.configuration.scheduler.config;

import com.gersimuca.erp.configuration.scheduler.job.Maintenance;
import jakarta.annotation.PostConstruct;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunnableInjector {
  private final Maintenance maintenance;

  @PostConstruct
  public void postConstruct() {
    for (JobType jobType : EnumSet.allOf(JobType.class)) {
      switch (jobType) {
        case MAINTENANCE -> jobType.setRunnable(maintenance);
      }
    }
  }
}
