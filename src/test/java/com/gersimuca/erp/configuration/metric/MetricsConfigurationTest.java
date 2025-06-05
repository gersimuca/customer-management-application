package com.gersimuca.erp.configuration.metric;

import static org.assertj.core.api.Assertions.assertThat;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MetricsConfigurationTest {

  @ParameterizedTest
  @ValueSource(strings = {"my_metric", "another", ""})
  @DisplayName("Counter meter names are prefixed with erp_")
  void counterMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Counter counter = registry.counter(metricName);
    assertThat(counter.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  @ParameterizedTest
  @ValueSource(strings = {"my_metric", "another", ""})
  @DisplayName("Gauge meter names are prefixed with erp_")
  void gaugeMeterNamesArePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Gauge gauge = Gauge.builder(metricName, () -> 1.0).register(registry);
    assertThat(gauge.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }

  @ParameterizedTest
  @ValueSource(strings = {"erp_already_prefixed"})
  @DisplayName("Already prefixed names are double-prefixed")
  void alreadyPrefixedNamesAreDoublePrefixed(String metricName) {
    MetricsConfiguration config = new MetricsConfiguration();
    MeterRegistry registry = new SimpleMeterRegistry();
    config.metrics().customize(registry);

    Counter counter = registry.counter(metricName);
    assertThat(counter.getId().getName()).isEqualTo(String.format("erp_%s", metricName));
  }
}
