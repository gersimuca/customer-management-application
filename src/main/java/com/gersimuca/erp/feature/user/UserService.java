package com.gersimuca.erp.feature.user;

import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;

public interface UserService {
  UserDto mustLoadByUsername(final String username);

  UserDto createUser(final UserDto userDto);

  List<CountryV3Dto> countryAvailableCountries();
}
