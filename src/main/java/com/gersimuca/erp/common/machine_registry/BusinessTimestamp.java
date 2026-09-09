package com.gersimuca.erp.common.machine_registry;

import jakarta.validation.constraints.NotNull;

public final class BusinessTimestamp {

  @NotNull private final Long value;

  public BusinessTimestamp(final Long value) {
    this.value = value;
  }

  public Long value() {
    return value;
  }

  public BusinessTimestamp next() {
    return new BusinessTimestamp(value + 1);
  }

  public static BusinessTimestamp max(final BusinessTimestamp left, final BusinessTimestamp right) {
    return left.value() >= right.value() ? left : right;
  }
}
