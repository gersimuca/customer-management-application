package com.gersimuca.erp.common.machine_registry;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public final class MachineId {

  @Min(0)
  @Max(63)
  private final Integer value;

  public MachineId(final Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }
}
