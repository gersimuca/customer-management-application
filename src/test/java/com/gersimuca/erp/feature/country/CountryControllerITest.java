package com.gersimuca.erp.feature.country;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gersimuca.erp.AuthenticatedMvcTest;
import com.gersimuca.erp.configuration.jpa.JwtTestData;
import com.gersimuca.erp.configuration.security.JwtAuthenticationConverter;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@AuthenticatedMvcTest
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CountryControllerITest {

  @Autowired private MockMvc mvc;

  @Test
  void testGetListOfCountriesEndpoint() throws Exception {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    ReflectionTestUtils.setField(converter, "clientId", "erp");
    String path = "/countries";
    mvc.perform(
            get(path)
                .with(
                    jwt()
                        .jwt(JwtTestData.JWT_NEW_USER)
                        .authorities(
                            jwt ->
                                Objects.requireNonNull(converter.convert(jwt)).getAuthorities())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(jsonPath("$.countries", hasSize(2)))
        .andExpect(jsonPath("$.countries[0].countryCode", is("AL")))
        .andExpect(jsonPath("$.countries[0].countryName", is("Albania")))
        .andExpect(jsonPath("$.countries[1].countryCode", is("IT")))
        .andExpect(jsonPath("$.countries[1].countryName", is("Italy")));
  }
}
