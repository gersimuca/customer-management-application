package com.gersimuca.erp.configuration.metric;

import static org.assertj.core.api.Assertions.assertThat;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MetricsConfigurationTest {

  static Stream<String> metricNames() {
    return Stream.of("my_metric", "another", "");
  }

  /** Counter: Monotonically increasing value, used to count discrete events. */
  @ParameterizedTest
  @MethodSource("metricNames")
  @DisplayName("Counter meter names are prefixed with erp_")
  void counterMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Counter counter = registry.counter(metricName);
    assertThat(counter.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  /** Gauge: Represents a value that can go up or down, like memory usage. */
  @ParameterizedTest
  @MethodSource("metricNames")
  @DisplayName("Gauge meter names are prefixed with erp_")
  void gaugeMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Gauge gauge = Gauge.builder(metricName, () -> 1.0).register(registry);
    assertThat(gauge.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  /** Timer: Measures the number and total time of events. */
  @ParameterizedTest
  @MethodSource("metricNames")
  @DisplayName("Timer meter names are prefixed with erp_")
  void timerMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Timer timer = registry.timer(metricName);
    assertThat(timer.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  /** DistributionSummary: Tracks the distribution of sample values (count, total, max, etc.). */
  @ParameterizedTest
  @MethodSource("metricNames")
  @DisplayName("DistributionSummary meter names are prefixed with erp_")
  void distributionSummaryMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    DistributionSummary summary = registry.summary(metricName);
    assertThat(summary.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  /** LongTaskTimer: Measures duration and count of long-running tasks. */
  @ParameterizedTest
  @MethodSource("metricNames")
  @DisplayName("LongTaskTimer meter names are prefixed with erp_")
  void longTaskTimerMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    LongTaskTimer longTaskTimer = registry.more().longTaskTimer(metricName);
    assertThat(longTaskTimer.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }
}
