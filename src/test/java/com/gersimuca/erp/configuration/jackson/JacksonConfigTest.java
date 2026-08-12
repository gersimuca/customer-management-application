package com.gersimuca.erp.configuration.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

class JacksonConfigTest {

  @Test
  void jsonNullableCustomizer_shouldRegisterModule() {

    JacksonConfig config = new JacksonConfig();

    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

    config.jsonNullableCustomizer().customize(builder);

    ObjectMapper mapper = builder.build();

    boolean containsModule =
        mapper.getRegisteredModuleIds().stream()
            .map(Object::toString)
            .anyMatch(id -> id.contains("JsonNullableModule"));

    assertThat(containsModule).isTrue();
  }
}
