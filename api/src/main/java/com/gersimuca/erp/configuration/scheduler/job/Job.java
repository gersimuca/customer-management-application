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

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

  protected final CronJobService service;

  public void performPre() {
    LoggerUtils.info(
        log, "--- Start -- {} {}", this.getClass().getName(), DATE_FORMAT.format(new Date()));
  }

  public void performJob() {
    LoggerUtils.info(
        log, "Job executed {} - {}", this.getClass().getName(), DATE_FORMAT.format(new Date()));
  }

  public void performPost() {
    LoggerUtils.info(
        log, "--- End -- {} {}", this.getClass().getName(), DATE_FORMAT.format(new Date()));
  }

  @Override
  @Transactional
  public void run() {
    performPre();
    performJob();
    performPost();
  }
}
