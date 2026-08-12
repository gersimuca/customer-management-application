package com.gersimuca.erp.configuration.jackson;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author gersimuca
 */
class JsonNullableSerializationTest {
  static class Sample {
    public JsonNullable<String> field = JsonNullable.undefined();
  }

  @Test
  void undefinedValueShouldBeOmitted() throws Exception {
    ObjectMapper mapper =
        new Jackson2ObjectMapperBuilder().modules(new JsonNullableModule()).build();

    String json = mapper.writeValueAsString(new Sample());

    assertThat(json).isEqualTo("{}");
  }

  @Test
  void presentValueShouldBeSerialized() throws Exception {
    ObjectMapper mapper =
        new Jackson2ObjectMapperBuilder().modules(new JsonNullableModule()).build();

    Sample sample = new Sample();
    sample.field = JsonNullable.of("hello");

    String json = mapper.writeValueAsString(sample);

    assertThat(json).isEqualTo("{\"field\":\"hello\"}");
  }
}
