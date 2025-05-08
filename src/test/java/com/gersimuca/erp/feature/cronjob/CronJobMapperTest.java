package com.gersimuca.erp.feature.cronjob;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CronJobMapperTest {
  private CronJobMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = Mappers.getMapper(CronJobMapper.class);
  }

  @Test
  void mapToDto() {
    CronJobEntity cronJobEntity = Instancio.create(CronJobEntity.class);
    CronJobDto cronJobDto = mapper.mapToDto(cronJobEntity);

    assertThat(cronJobDto.getCronJobId()).isEqualTo(cronJobEntity.getCronJobId());
    assertThat(cronJobDto.getName()).isEqualTo(cronJobEntity.getName());
    assertThat(cronJobDto.getScheduledTime()).isEqualTo(cronJobEntity.getScheduledTime());
  }

  @Test
  void mapToListDto() {
    List<CronJobEntity> cronJobEntities = Instancio.ofList(CronJobEntity.class).size(5).create();
    List<CronJobDto> cronJobDtos = mapper.mapToListDto(cronJobEntities);

    List<CronJobDto> expectedDtos =
        cronJobEntities.stream()
            .map(
                entity ->
                    new CronJobDto(
                        entity.getCronJobId(), entity.getName(), entity.getScheduledTime()))
            .toList();

    assertThat(cronJobDtos).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedDtos);
  }
}
