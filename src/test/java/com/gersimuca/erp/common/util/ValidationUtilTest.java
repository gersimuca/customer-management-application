package com.gersimuca.erp.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ValidationUtilTest {

  @Mock private Validator validator;
  @Mock private ConstraintViolation<Object> violation;

  private Object validObject;
  private Object invalidObject;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    validObject = new Object();
    invalidObject = new Object();
  }

  @Test
  void validate_doesNotThrowException_whenObjectIsValid() {
    when(validator.validate(validObject)).thenReturn(Collections.emptySet());

    ValidationUtil.validate(validator, validObject);
  }

  @Test
  void validate_throwsConstraintViolationException_whenObjectIsInvalid() {
    Set<ConstraintViolation<Object>> violations = Set.of(violation);
    when(validator.validate(invalidObject)).thenReturn(violations);

    assertThrows(
        ConstraintViolationException.class,
        () -> ValidationUtil.validate(validator, invalidObject));
  }

  @Test
  void validate_throwsConstraintViolationException_withCorrectViolations() {
    Set<ConstraintViolation<Object>> violations = Set.of(violation);
    when(validator.validate(invalidObject)).thenReturn(violations);

    ConstraintViolationException exception =
        assertThrows(
            ConstraintViolationException.class,
            () -> ValidationUtil.validate(validator, invalidObject));
    assertEquals(violations, exception.getConstraintViolations());
  }

  @Test
  void validate_doesNotThrowException_whenArrayIsValid() {
    Object[] validObjects = {validObject, validObject};
    when(validator.validate(validObject)).thenReturn(Collections.emptySet());

    ValidationUtil.validate(validator, validObjects);
  }

  @Test
  void validate_throwsConstraintViolationException_whenArrayContainsInvalidObject() {
    Object[] objects = {validObject, invalidObject};
    Set<ConstraintViolation<Object>> violations = Set.of(violation);
    when(validator.validate(validObject)).thenReturn(Collections.emptySet());
    when(validator.validate(invalidObject)).thenReturn(violations);

    assertThrows(
        ConstraintViolationException.class, () -> ValidationUtil.validate(validator, objects));
  }

  @Test
  void validate_throwsConstraintViolationException_withCorrectViolationsForArray() {
    Object[] objects = {validObject, invalidObject};
    Set<ConstraintViolation<Object>> violations = Set.of(violation);
    when(validator.validate(validObject)).thenReturn(Collections.emptySet());
    when(validator.validate(invalidObject)).thenReturn(violations);

    ConstraintViolationException exception =
        assertThrows(
            ConstraintViolationException.class, () -> ValidationUtil.validate(validator, objects));
    assertEquals(violations, exception.getConstraintViolations());
  }
}
