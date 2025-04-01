package com.gersimuca.erp.common.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Named;

public class MapperUtil {
  private MapperUtil() {}

  /**
   * Map offset datetime to UTC timezone
   *
   * @param offsetDateTime the offset datetime
   * @return the UTC offset datetime
   */
  @Named("toUTC")
  public static OffsetDateTime toUTC(OffsetDateTime offsetDateTime) {
    if (offsetDateTime == null) {
      return null;
    }
    return offsetDateTime.toInstant().atOffset(ZoneOffset.UTC);
  }

  @Named("stringToLong")
  public static Long stringToLong(String value) {
    try {
      return value != null ? Long.valueOf(value) : null;
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @Named("longToString")
  public static String longToString(Long value) {
    return value != null ? value.toString() : null;
  }

  /**
   * Map value to boolean if it exists or not
   *
   * @param value the value
   * @return the boolean
   */
  @Named("mapValueExists")
  public static Boolean mapValueExists(String value) {
    return value != null && !value.isEmpty();
  }
}
