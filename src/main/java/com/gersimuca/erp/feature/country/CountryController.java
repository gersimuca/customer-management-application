package com.gersimuca.erp.feature.country;

import static com.gersimuca.erp.common.AuthorizationExpressions.IS_AUTHORIZED;

import com.gersimuca.erp.api.CountriesApi;
import com.gersimuca.erp.model.CountriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryController implements CountriesApi {

  @Qualifier("countryServiceImpl")
  private final CountryService service;

  private final CountryMapper mapper;

  @Override
  @PreAuthorize(IS_AUTHORIZED)
  public ResponseEntity<CountriesResponse> getCountriesAvailable() {
    return ResponseEntity.ok(new CountriesResponse().countries(mapper.toModel(service.findAll())));
  }
}
