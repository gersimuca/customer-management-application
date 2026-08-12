package com.gersimuca.erp.feature.country;

import java.util.List;

public interface CountryService {
  List<CountryDto> findAll();

  List<CountryDto> registerCountry();
}
