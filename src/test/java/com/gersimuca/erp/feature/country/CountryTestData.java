package com.gersimuca.erp.feature.country;

import com.gersimuca.erp.model.CountryModel;
import java.util.List;

public class CountryTestData {

  private CountryTestData() {}

  public static final CountryEntity ALBANIA_ENTITY =
      CountryEntity.builder().countryName("Albania").countryCode("AL").build();
  public static final CountryDto ALBANIA_DTO =
      CountryDto.builder().countryName("Albania").countryCode("AL").build();
  public static final CountryEntity ITALY_ENTITY =
      CountryEntity.builder().countryName("Italy").countryCode("IT").build();
  public static final CountryDto ITALY_DTO =
      CountryDto.builder().countryName("Italy").countryCode("IT").build();
  public static final CountryModel ALBANIA_MODEL = new CountryModel(null, "ALBANIA", "AL");
  public static final CountryModel ITALY_MODEL = new CountryModel(null, "Italy", "IT");

  public List<CountryEntity> countryEntities() {
    return List.of(ALBANIA_ENTITY, ITALY_ENTITY);
  }

  public List<CountryDto> countryDtos() {
    return List.of(ALBANIA_DTO, ITALY_DTO);
  }

  public List<CountryModel> countryModels() {
    return List.of(ALBANIA_MODEL, ITALY_MODEL);
  }
}
