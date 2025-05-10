package com.gersimuca.erp.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class MapperUtilTest {

  @Test
  void testToUTC_withValidOffsetDateTime() {
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.ofHours(5));
    OffsetDateTime utcTime = MapperUtil.toUTC(now);

    assertNotNull(utcTime);
    assertEquals(ZoneOffset.UTC, utcTime.getOffset());
    assertEquals(now.toInstant(), utcTime.toInstant());
  }

  @Test
  void testToUTC_withNull() {
    assertNull(MapperUtil.toUTC(null));
  }

  @Test
  void testStringToLong_withValidString() {
    assertEquals(123456789L, MapperUtil.stringToLong("123456789"));
  }

  @Test
  void testStringToLong_withNull() {
    assertNull(MapperUtil.stringToLong(null));
  }

  @Test
  void testStringToLong_withInvalidString() {
    assertNull(MapperUtil.stringToLong("abc"));
  }

  @Test
  void testLongToString_withValidLong() {
    assertEquals("987654321", MapperUtil.longToString(987654321L));
  }

  @Test
  void testLongToString_withNull() {
    assertNull(MapperUtil.longToString(null));
  }

  @Test
  void testMapValueExists_withNonEmptyString() {
    assertTrue(MapperUtil.mapValueExists("hello"));
  }

  @Test
  void testMapValueExists_withEmptyString() {
    assertFalse(MapperUtil.mapValueExists(""));
  }

  @Test
  void testMapValueExists_withNull() {
    assertFalse(MapperUtil.mapValueExists(null));
  }
}
