package com.gersimuca.erp.feature.machine_registry.stale;

import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.feature.machine_registry.MachineRegistryService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineRegistryStaleCleanupServiceImpl implements MachineRegistryStaleCleanupService {

  private final MachineRegistryService service;

  @Override
  @Scheduled(fixedRate = 60_000)
  public void cleanup() {
    Instant cutoff = Instant.now().minusSeconds(120);
    LoggerUtils.info(log, "Cleaning stale machine registry entries older than {}", cutoff);
    service.cleanupInactiveMachines(cutoff);
  }
}
