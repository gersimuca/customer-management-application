package com.gersimuca.erp.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class UTCOffsetDateTimeValidator
    implements ConstraintValidator<UTCOffsetDateTime, OffsetDateTime> {
  @Override
  public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return value.getOffset().equals(ZoneOffset.UTC);
  }
}
