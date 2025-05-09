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

  public Maintenance(CronJobService service) {
    super(service);
  }

  public void execute(SimpleDateFormat dateFormat) {
    LoggerUtils.info(
        log, "Executing Job {} - {}", dateFormat.format(new Date()), this.getClass().getName());
  }

  @Async
  @Override
  public void performJob(SimpleDateFormat dateFormat) {
    super.performJob(dateFormat);
    execute(dateFormat);
  }
}
