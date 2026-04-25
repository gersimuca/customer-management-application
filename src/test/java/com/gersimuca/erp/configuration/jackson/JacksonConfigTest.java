package com.gersimuca.erp.configuration.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author gersimuca
 */
class JacksonConfigTest {

  @Test
  void jsonNullableCustomizer_shouldRegisterJsonNullableModule() {
    JacksonConfig config = new JacksonConfig();
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

    config.jsonNullableCustomizer().customize(builder);
    ObjectMapper mapper = builder.build();

    boolean containsNullableModule =
        mapper.getRegisteredModuleIds().stream()
            .map(Object::toString)
            .anyMatch(id -> id.contains(JsonNullableModule.class.getSimpleName()));

    assertThat(containsNullableModule)
        .as("JsonNullableModule should be registered in ObjectMapper")
        .isTrue();
  }
}
