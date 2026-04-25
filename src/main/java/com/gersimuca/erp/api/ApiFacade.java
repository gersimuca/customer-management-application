package com.gersimuca.erp.api;

import com.gersimuca.erp.api.external.nagerdate.NagerdateService;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiFacade {
  private final NagerdateService nagerdateService;

  public List<CountryV3Dto> countryAvailableCountries() {
    return nagerdateService.countryAvailableCountries();
  }
}
