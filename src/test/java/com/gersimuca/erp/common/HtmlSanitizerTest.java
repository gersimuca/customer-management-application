package com.gersimuca.erp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

class HtmlSanitizerTest {

  @InjectMocks private HtmlSanitizer sanitizer;

  @BeforeEach
  void setUp() {
    sanitizer = new HtmlSanitizer();
  }

  @ParameterizedTest
  @CsvSource({
    "'<p>This is a <strong>test</strong>.</p>', 'This is a test.'",
    "'', ''",
    "'<script>alert(''test'');</script>', ''",
    "'<div><p>This is <span>a</span> test.</p></div>', 'This is a test.'"
  })
  void sanitize(String input, String expected) {
    assertEquals(expected, sanitizer.sanitize(input));
  }

  @Test
  void sanitize_handlesNullInput() {
    assertEquals(StringUtils.EMPTY, sanitizer.sanitize(null));
  }
}
