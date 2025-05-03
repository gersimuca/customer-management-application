package com.gersimuca.erp.feature.cronjob;

import com.gersimuca.erp.common.MapperConfig;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CronJobMapper {
  CronJobDto mapToDto(CronJobEntity cronJobEntity);

  List<CronJobDto> mapToListDto(List<CronJobEntity> cronJobEntities);
}
