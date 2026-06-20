package com.gersimuca.erp.feature.machine_registry;

import com.gersimuca.erp.common.machine_registry.MachineId;
import java.util.concurrent.atomic.AtomicReference;

import com.gersimuca.erp.common.util.LoggerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MachineContext {

  private final MachineRegistryService machineRegistryService;

  private static final String SERVICE_NAME = "erp-api-service";

  private final AtomicReference<MachineId> machineId = new AtomicReference<>();

  public MachineId getMachineId() {
    MachineId current = machineId.get();
    if (current != null) {
      return current;
    }
    synchronized (this) {
      current = machineId.get();
      if (current == null) {
        current = machineRegistryService.allocateMachineId(SERVICE_NAME);
        machineId.set(current);
      }
      LoggerUtils.info(log, "Allocated machineId={} for instance", current.value());
      return current;
    }
  }
}