package com.gersimuca.erp.common.machine_registry;

// @Component
// @RequiredArgsConstructor
// public class MachineIdAllocator {

  //  private final JdbcTemplate jdbcTemplate;
  //
  //  public long allocate(final String instanceId, final String serviceName) {
  //
  //    for (int i = 0; i < 64; i++) {
  //      if (tryClaim(i, instanceId, serviceName)) {
  //        return i;
  //      }
  //    }
  //
  //    throw new IllegalStateException("No available machine IDs (0–63)");
  //  }
  //
  //  private boolean tryClaim(
  //      final long machineId, final String instanceId, final String serviceName) {
  //    try {
  //      jdbcTemplate.update(
  //          """
  //                INSERT INTO machine_registry (machine_id, instance_id, service_name)
  //                VALUES (?, ?, ?)
  //            """,
  //          machineId,
  //          instanceId,
  //          serviceName);
  //
  //      return true;
  //
  //    } catch (final Exception e) {
  //      return false;
  //    }
  //  }
// }
