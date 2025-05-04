package com.gersimuca.erp.configuration;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gersimuca.erp.AuthenticatedMvcTest;
import com.gersimuca.erp.common.exception.BaseException;
import com.gersimuca.erp.common.exception.EntityNotFoundException;
import com.gersimuca.erp.common.exception.ErrorSeverity;
import com.gersimuca.erp.feature.user.UserEntity;
import java.util.Objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@AuthenticatedMvcTest
@SpringBootTest
class GeneralExceptionHandlerITest {

  @Autowired private MockMvc mvc;

  @Test
  void handleBaseException() throws Exception {
    final HttpStatus notFound = HttpStatus.NOT_FOUND;
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    ReflectionTestUtils.setField(converter, "clientId", "engdci");
    final EntityNotFoundException entityNotFoundException =
        new EntityNotFoundException(UserEntity.class, "U159785");
    mvc.perform(
            get("/users/current-user")
                .contentType(MediaType.APPLICATION_JSON)
                .with(
                    jwt()
                        .jwt(JwtTestData.JWT_NOT_EXISTING_USERNAME)
                        .authorities()
                        .authorities(
                            jwt -> Objects.requireNonNull(converter.convert(jwt)).getAuthorities()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status", is(notFound.value())))
        .andExpect(jsonPath("$.code", is(notFound.getReasonPhrase())))
        .andExpect(jsonPath("$.description", is(entityNotFoundException.getMessage())));
  }

  @WithMockUser(username = "iron.man")
  @Test
  @Disabled
  void handleAccessDeniedException() throws Exception {
    final HttpStatus forbidden = HttpStatus.FORBIDDEN;
    final BaseException baseException =
        new BaseException("Access Denied", forbidden, ErrorSeverity.WARN);
    mvc.perform(get("/users/current-user"))
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$.status", is(forbidden.value())))
        .andExpect(jsonPath("$.code", is(forbidden.getReasonPhrase())))
        .andExpect(jsonPath("$.description", is(baseException.getMessage())));
  }

  @Test
  void handleConstraintViolationException() throws Exception {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    ReflectionTestUtils.setField(converter, "clientId", "engdci");
    mvc.perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(
                    jwt()
                        .jwt(JwtTestData.JWT_INVALID_USERNAME)
                        .authorities()
                        .authorities(
                            jwt -> Objects.requireNonNull(converter.convert(jwt)).getAuthorities()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.getReasonPhrase())));
  }
}
