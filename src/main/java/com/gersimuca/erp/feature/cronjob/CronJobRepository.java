package com.gersimuca.erp.feature.cronjob;

import com.gersimuca.erp.common.repository.BaseRepository;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.List;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

public interface CronJobRepository extends BaseRepository<CronJobEntity, Long> {
  @Override
  @Lock(LockModeType.PESSIMISTIC_READ)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  List<CronJobEntity> findAll();

  @Lock(LockModeType.PESSIMISTIC_READ)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  CronJobEntity findByName(String name);

  @Override
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  <S extends CronJobEntity> S save(S entity);

  @Lock(LockModeType.PESSIMISTIC_READ)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  List<CronJobEntity> findByNameIn(List<String> names);
}
