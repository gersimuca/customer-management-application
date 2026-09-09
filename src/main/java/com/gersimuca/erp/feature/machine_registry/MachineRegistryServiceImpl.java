package com.gersimuca.erp.feature.machine_registry;

// @Service
// @RequiredArgsConstructor
// @Transactional(readOnly = true)
// public class MachineRegistryServiceImpl implements MachineRegistryService {
//
//  private static final Integer MAX_MACHINE_COUNT = 64;
//
//  private final MachineRegistryRepository repository;
//
//  @Transactional
//  public MachineId allocateMachineId(final String serviceName) {
//    final String instanceId = UUID.randomUUID().toString();
//    for (long candidate = 0; candidate < MAX_MACHINE_COUNT; candidate++) {
//      try {
//        repository.save(
//            MachineRegistryEntity.builder()
//                .machineId(candidate)
//                .instanceId(instanceId)
//                .serviceName(serviceName)
//                .allocatedAt(Instant.now())
//                .lastHeartbeat(Instant.now())
//                .build());
//        return new MachineId(candidate);
//      } catch (DataIntegrityViolationException ignored) {
//      }
//    }
//
//    throw new IllegalStateException("No available machine ids");
//  }
//
//  @Transactional
//  public synchronized void updateHeartbeat(final MachineId machineId) {
//    repository.updateHeartbeat(Math.toIntExact(machineId.value()), Instant.now());
//  }
//
//  @Transactional
//  public synchronized void cleanupInactiveMachines(final Instant cutOff) {
//    repository.cleanup(cutOff);
//  }
// }
