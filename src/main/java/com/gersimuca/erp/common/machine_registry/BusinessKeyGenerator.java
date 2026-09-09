// package com.gersimuca.erp.common.machine_registry;
//
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Component;
//
// @Component
// @RequiredArgsConstructor
// public class BusinessKeyGenerator {
//
//  private static final Integer MACHINE_BITS = 6;
//  private static final Integer SEQUENCE_BITS = 6;
//  private static final Integer MACHINE_SHIFT = SEQUENCE_BITS;
//  private static final Integer TIME_SHIFT = MACHINE_BITS + SEQUENCE_BITS;
//  private final com.gersimuca.erp.common.machine_registry.MachineContext machineContext;
//  private final ClusterClock clock;
//  private BusinessTimestamp logicalTimestamp = new BusinessTimestamp(0L);
//  private BusinessSequence sequence = new BusinessSequence(0L);
//
//  public synchronized Long nextId() {
//
//    final BusinessTimestamp current = clock.now();
//    final BusinessTimestamp nextTimestamp = BusinessTimestamp.max(logicalTimestamp.next(),
// current);
//
//    if (nextTimestamp.value().equals(logicalTimestamp.value())) {
//      sequence = sequence.next();
//      if (sequence.overflow()) {
//        logicalTimestamp = logicalTimestamp.next();
//        sequence = sequence.reset();
//      }
//    } else {
//      sequence = sequence.reset();
//      logicalTimestamp = nextTimestamp;
//    }
//
//    return encode(logicalTimestamp, machineContext.getMachineId(), sequence);
//  }
//
//  private Long encode(
//      final BusinessTimestamp timestamp,
//      final MachineId machineId,
//      final BusinessSequence sequence) {
//
//    return (timestamp.value() << TIME_SHIFT)
//        | (machineId.value() << MACHINE_SHIFT)
//        | sequence.value();
//  }
// }
