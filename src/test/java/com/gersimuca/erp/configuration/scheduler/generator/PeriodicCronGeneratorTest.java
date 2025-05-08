package com.gersimuca.erp.configuration.scheduler.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PeriodicCronGeneratorTest {

  @Test
  void testCreateScheduleExpression_allNulls() {
    PeriodicCronGenerator generator = PeriodicCronGenerator.builder().build();
    assertEquals("* * * * * *", generator.createScheduleExpression());
  }

  @Test
  void testCreateScheduleExpression_onlyHours() {
    PeriodicCronGenerator generator = PeriodicCronGenerator.builder().hours(5L).build();
    assertEquals("* * */5 * * *", generator.createScheduleExpression());
  }

  @Test
  void testCreateScheduleExpression_onlyMinutes() {
    PeriodicCronGenerator generator = PeriodicCronGenerator.builder().minutes(3L).build();
    assertEquals("* */3 * * * *", generator.createScheduleExpression());
  }

  @Test
  void testCreateScheduleExpression_onlySeconds() {
    PeriodicCronGenerator generator = PeriodicCronGenerator.builder().seconds(10L).build();
    assertEquals("*/10 * * * * *", generator.createScheduleExpression());
  }

  @Test
  void testCreateScheduleExpression_allSet() {
    PeriodicCronGenerator generator =
        PeriodicCronGenerator.builder().hours(2L).minutes(4L).seconds(6L).build();
    assertEquals("*/6 */4 */2 * * *", generator.createScheduleExpression());
  }

  @Test
  void testCreateScheduleExpression_allOnes() {
    PeriodicCronGenerator generator =
        PeriodicCronGenerator.builder().hours(1L).minutes(1L).seconds(1L).build();
    assertEquals("* * * * * *", generator.createScheduleExpression());
  }
}
