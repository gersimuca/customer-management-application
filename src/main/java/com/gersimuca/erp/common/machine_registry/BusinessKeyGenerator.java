package com.gersimuca.erp.common.machine_registry;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessKeyGenerator {

  private final MachineContext machineContext;

  private static final long EPOCH = 1735689600000L; // 2025-01-01

  private static final long MACHINE_BITS = 6;
  private static final long SEQUENCE_BITS = 6;

  private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

  private static final long SHIFT = MACHINE_BITS + SEQUENCE_BITS;

  private long lastTimestamp = -1L;
  private long sequence = 0L;

  public synchronized long nextId() {

    long now = timestamp();

    if (now == lastTimestamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;

      if (sequence == 0) {
        while ((now = timestamp()) <= lastTimestamp) {}
      }
    } else {
      sequence = 0;
    }

    lastTimestamp = now;

    return (now << SHIFT) | (machineContext.getMachineId() << SEQUENCE_BITS) | sequence;
  }

  private long timestamp() {
    return System.currentTimeMillis() - EPOCH;
  }
}
