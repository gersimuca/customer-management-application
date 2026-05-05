package com.gersimuca.erp.common.machine_registry;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MachineCleanupJob {

  private final JdbcTemplate jdbcTemplate;

  @Scheduled(fixedRate = 60000)
  public void cleanup() {
    jdbcTemplate.update(
        """
            DELETE FROM machine_registry
            WHERE last_heartbeat < DATEADD(MINUTE, -2, CURRENT_TIMESTAMP)
        """);
  }
}
