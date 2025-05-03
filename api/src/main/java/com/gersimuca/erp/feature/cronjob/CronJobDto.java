package com.gersimuca.erp.feature.cronjob;

import com.gersimuca.erp.common.annotation.DataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DataTransferObject
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CronJobDto {
  private Long cronJobId;
  private String name;
  private String scheduledTime;
}
