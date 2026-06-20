package com.gersimuca.erp.feature.machine_registry;

import com.gersimuca.erp.common.machine_registry.MachineId;
import java.time.Instant;

public interface MachineRegistryService {
  MachineId allocateMachineId(final String serviceName);

  void updateHeartbeat(final MachineId machineId);

  void cleanupInactiveMachines(Instant cutOff);
}
