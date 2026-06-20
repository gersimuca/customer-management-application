package com.gersimuca.erp.common.machine_registry;

import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class ClusterClock {

  private static final Long EPOCH = 1735689600000L;

  public BusinessTimestamp now() {

    return new BusinessTimestamp(Instant.now().toEpochMilli() - EPOCH);
  }
}
