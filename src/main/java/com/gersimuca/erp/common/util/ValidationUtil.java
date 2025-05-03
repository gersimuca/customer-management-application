package com.gersimuca.erp.common.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Arrays;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

  /**
   * Validates the given object using the provided Validator. If there are any constraint
   * violations, a ConstraintViolationException is thrown.
   *
   * @param <T> the type of the object to validate
   * @param validator the Validator to use for validation
   * @param object the object to validate
   * @throws ConstraintViolationException if there are any constraint violations
   */
  public static <T> void validate(final Validator validator, final T object) {
    final Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  /**
   * Validates an array of objects using the provided Validator. If there are any constraint
   * violations in any of the objects, a ConstraintViolationException is thrown.
   *
   * @param <T> the type of the objects to validate
   * @param validator the Validator to use for validation
   * @param objects the array of objects to validate
   * @throws ConstraintViolationException if there are any constraint violations in any of the
   *     objects
   */
  public static <T> void validate(final Validator validator, final T[] objects) {
    Arrays.stream(objects).forEachOrdered(object -> validate(validator, object));
  }
}
