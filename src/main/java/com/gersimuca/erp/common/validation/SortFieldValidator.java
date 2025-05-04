package com.gersimuca.erp.common.validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SortFieldValidator {

  /**
   * Get allowed fields for class or projection interface
   *
   * @param type the class type
   * @return the set of allowed fields
   */
  public static Set<String> getAllowedFields(Class<?> type) {
    Set<String> fields = new HashSet<>();

    if (type.isInterface()) {
      // Extract from projection interface getters
      for (Method method : type.getMethods()) {
        if (method.getName().startsWith("get")) {
          String methodName = method.getName().substring(3); // Remove "get"
          String fieldName = Character.toLowerCase(methodName.charAt(0)) + methodName.substring(1);
          fields.add(fieldName);
        }
      }
    } else {
      // Extract from entity class fields
      Class<?> currentType = type;
      while (currentType != null && !currentType.equals(Object.class)) {
        for (Field field : currentType.getDeclaredFields()) {
          fields.add(field.getName());
        }
        currentType = currentType.getSuperclass();
      }
    }
    return fields;
  }

  /**
   * Validate sort field belongs to class or projection interface
   *
   * @param field the field name
   * @param type the class type
   */
  public static void validateSortField(String field, Class<?> type) {
    Set<String> allowedFields = getAllowedFields(type);
    if (!allowedFields.contains(field)) {
      throw new IllegalArgumentException("Invalid sort field: " + field);
    }
  }
}
