package com.gersimuca.erp.common.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class SortUtilTest {
  @Test
  void testParseSort_withValidAscendingField() {
    Sort sort = SortUtil.parseSort("+name", DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isFalse();
    Assertions.assertThat(sort.stream().count()).isOne();

    Sort.Order order = sort.getOrderFor("name");
    Assertions.assertThat(order).isNotNull();
    Assertions.assertThat(order.getDirection()).isEqualTo(Sort.Direction.ASC);
  }

  @Test
  void testParseSort_withValidDescendingField() {
    Sort sort = SortUtil.parseSort("-description", DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isFalse();
    Assertions.assertThat(sort.stream().count()).isOne();

    Sort.Order order = sort.getOrderFor("description");
    Assertions.assertThat(order).isNotNull();
    Assertions.assertThat(order.getDirection()).isEqualTo(Sort.Direction.DESC);
  }

  @Test
  void testParseSort_withMultipleFields() {
    Sort sort = SortUtil.parseSort("+name,-description", DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isFalse();
    Assertions.assertThat(sort.stream().count()).isEqualTo(2);

    Sort.Order order1 = sort.getOrderFor("name");
    Sort.Order order2 = sort.getOrderFor("description");
    Assertions.assertThat(order1).isNotNull();
    Assertions.assertThat(order2).isNotNull();
    Assertions.assertThat(order1.getDirection()).isEqualTo(Sort.Direction.ASC);
    Assertions.assertThat(order2.getDirection()).isEqualTo(Sort.Direction.DESC);
  }

  @Test
  void testParseSort_withUnsorted() {
    Sort sort = SortUtil.parseSort(null, DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isTrue();
  }

  @Test
  void testParseSort_withEmptyString() {
    Sort sort = SortUtil.parseSort("", DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isTrue();
  }

  @Test
  void testParseSort_withInvalidField() {
    Assertions.assertThatThrownBy(() -> SortUtil.parseSort("-invalidField", DummyClass.class))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Invalid sort field: invalidField");
  }

  @Test
  void testParseSort_withNoPrefix() {
    Sort sort = SortUtil.parseSort("name", DummyClass.class);

    Assertions.assertThat(sort).isNotNull();
    Assertions.assertThat(sort.isUnsorted()).isFalse();
    Assertions.assertThat(sort.stream().count()).isOne();

    Sort.Order order = sort.getOrderFor("name");
    Assertions.assertThat(order).isNotNull();
    Assertions.assertThat(order.getDirection()).isEqualTo(Sort.Direction.ASC);
  }
}
