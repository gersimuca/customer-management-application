package com.gersimuca.erp.common.machine_registry;

// import com.gersimuca.erp.common.util.LoggerUtils;
// import jakarta.annotation.PostConstruct;
// import java.util.UUID;
// import lombok.Getter;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Component;
//
// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class MachineContext {
//
//  private final MachineIdAllocator allocator;
//
//  @Getter private Long machineId;
//
//  @Getter private String instanceId;
//
//  @PostConstruct
//  public void init() {
//    this.instanceId = UUID.randomUUID().toString();
//    this.machineId = allocator.allocate(instanceId, "user-service");
//    LoggerUtils.info(log, "Allocated machineId = {}", machineId);
//  }
// }
