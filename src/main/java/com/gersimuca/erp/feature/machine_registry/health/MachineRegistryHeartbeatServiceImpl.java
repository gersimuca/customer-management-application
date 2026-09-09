package com.gersimuca.erp.feature.machine_registry.health;

import com.gersimuca.erp.common.machine_registry.MachineId;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.feature.machine_registry.MachineRegistryService;
import com.gersimuca.erp.feature.machine_registry.state.MachineContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MachineRegistryHeartbeatServiceImpl implements MachineRegistryHeartbeatService {

  private final MachineRegistryService service;
  private final MachineContext context;

  @Override
  @Scheduled(fixedRate = 10_000)
  public void sendHeartbeat() {
    MachineId machineId = context.getMachineId();
    service.updateHeartbeat(machineId);
    LoggerUtils.debug(log, "Heartbeat sent for machineId={}", machineId.value());
  }
}
