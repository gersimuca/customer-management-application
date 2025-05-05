package com.gersimuca.erp.configuration.scheduler.job;

import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.feature.cronjob.CronJobService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Maintenance extends Job {
  private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

  public Maintenance(CronJobService service) {
    super(service);
  }

  public void execute() {
    LoggerUtils.info(
        log, "Executing Job {} - {}", DATE_FORMAT.format(new Date()), this.getClass().getName());
  }

  @Async
  @Override
  public void performJob() {
    execute();
  }
}
