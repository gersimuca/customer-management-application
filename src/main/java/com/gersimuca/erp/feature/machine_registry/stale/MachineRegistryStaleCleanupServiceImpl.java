package com.gersimuca.erp.feature.machine_registry.stale;

// @Service
// @RequiredArgsConstructor
// @Slf4j
// public class MachineRegistryStaleCleanupServiceImpl implements MachineRegistryStaleCleanupService
// {
//
//  private final MachineRegistryService service;
//
//  @Override
//  @Scheduled(fixedRate = 60_000)
//  public void cleanup() {
//    Instant cutoff = Instant.now().minusSeconds(120);
//    LoggerUtils.info(log, "Cleaning stale machine registry entries older than {}", cutoff);
//    service.cleanupInactiveMachines(cutoff);
//  }
// }
