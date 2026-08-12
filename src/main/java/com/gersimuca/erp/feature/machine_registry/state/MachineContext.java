package com.gersimuca.erp.feature.machine_registry.state;

import com.gersimuca.erp.common.machine_registry.MachineId;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.feature.machine_registry.MachineRegistryService;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MachineContext {

  private final MachineRegistryService machineRegistryService;
  private final String podName;
  private final AtomicReference<MachineId> machineId = new AtomicReference<>();

  public MachineId getMachineId() {
    MachineId current = machineId.get();
    if (current != null) {
      return current;
    }

    synchronized (this) {
      current = machineId.get();
      if (current == null) {
        current = machineRegistryService.allocateMachineId(podName);
        machineId.set(current);
        LoggerUtils.info(log, "Allocated machineId={} for pod={}", current.value(), podName);
      }
      return current;
    }
  }
}
