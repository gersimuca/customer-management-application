package com.gersimuca.erp.configuration.scheduler.job;

import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.feature.cronjob.CronJobService;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public abstract class Job implements Runnable {

  protected final CronJobService service;

  public void performPre(SimpleDateFormat dateFormat) {
    LoggerUtils.info(
        log, "--- Start -- {} {}", this.getClass().getName(), dateFormat.format(new Date()));
  }

  public void performJob(SimpleDateFormat dateFormat) {
    LoggerUtils.info(
        log, "Job executed {} - {}", this.getClass().getName(), dateFormat.format(new Date()));
  }

  public void performPost(SimpleDateFormat dateFormat) {
    LoggerUtils.info(
        log, "--- End -- {} {}", this.getClass().getName(), dateFormat.format(new Date()));
  }

  @Override
  @Transactional
  public void run() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    performPre(dateFormat);
    performJob(dateFormat);
    performPost(dateFormat);
  }
}
