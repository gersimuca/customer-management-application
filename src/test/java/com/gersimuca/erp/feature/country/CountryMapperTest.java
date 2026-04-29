package com.gersimuca.erp.feature.country;

import static org.assertj.core.api.Assertions.assertThat;

import com.gersimuca.erp.model.CountryModel;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CountryMapperTest {

  private CountryMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = Mappers.getMapper(CountryMapper.class);
  }

  @Test
  void shouldMapEntityToDto() {
    CountryDto dto = mapper.entityToDto(CountryTestData.ALBANIA_ENTITY);
    assertThat(dto).isNotNull();
    assertThat(dto.getCountryName()).isEqualTo("Albania");
    assertThat(dto.getCountryCode()).isEqualTo("AL");
  }

  @Test
  void shouldMapEntityListToDtoList() {
    List<CountryDto> dtos =
        mapper.entityToDto(List.of(CountryTestData.ALBANIA_ENTITY, CountryTestData.ITALY_ENTITY));

    assertThat(dtos).hasSize(2);

    assertThat(dtos.get(0).getCountryCode()).isEqualTo("AL");
    assertThat(dtos.get(0).getCountryName()).isEqualTo("Albania");

    assertThat(dtos.get(1).getCountryCode()).isEqualTo("IT");
    assertThat(dtos.get(1).getCountryName()).isEqualTo("Italy");
  }

  @Test
  void shouldMapExternalDtoToDto() {
    CountryV3Dto external = new CountryV3Dto("AL", "Albania");
    CountryDto dto = mapper.externalToDto(external);
    assertThat(dto).isNotNull();
    assertThat(dto.getCountryName()).isEqualTo("Albania");
    assertThat(dto.getCountryCode()).isEqualTo("AL");
    assertThat(dto.getCountryId()).isNull();
  }

  @Test
  void shouldMapDtoToEntity() {
    CountryEntity entity = mapper.toEntity(CountryTestData.ALBANIA_DTO);
    assertThat(entity).isNotNull();
    assertThat(entity.getCountryName()).isEqualTo("Albania");
    assertThat(entity.getCountryCode()).isEqualTo("AL");
  }

  @Test
  void shouldUpdateEntityIgnoringNulls() {
    CountryEntity entity =
        CountryEntity.builder().countryName("Old Name").countryCode("ON").build();

    CountryDto dto = CountryDto.builder().countryName("Albania").countryCode(null).build();

    mapper.updateEntity(dto, entity);

    assertThat(entity.getCountryName()).isEqualTo("Albania");
    assertThat(entity.getCountryCode()).isEqualTo("ON");
  }

  @Test
  void shouldMapDtoToModel() {
    CountryModel model = mapper.toModel(CountryTestData.ALBANIA_DTO);

    assertThat(model).isNotNull();

    assertThat(model.getCountryId()).isNull();

    assertThat(model.getCountryName()).isEqualTo(CountryTestData.ALBANIA_DTO.getCountryName());

    assertThat(model.getCountryCode()).isEqualTo(CountryTestData.ALBANIA_DTO.getCountryCode());
  }

  @Test
  void shouldMapDtoListToModelList() {
    List<CountryModel> models =
        mapper.toModel(List.of(CountryTestData.ALBANIA_DTO, CountryTestData.ITALY_DTO));

    assertThat(models).isNotNull().hasSize(2);

    CountryModel albania = models.get(0);
    CountryModel italy = models.get(1);

    assertThat(albania.getCountryId()).isNull();
    assertThat(albania.getCountryName()).isEqualTo(CountryTestData.ALBANIA_DTO.getCountryName());
    assertThat(albania.getCountryCode()).isEqualTo(CountryTestData.ALBANIA_DTO.getCountryCode());

    assertThat(italy.getCountryId()).isNull();
    assertThat(italy.getCountryName()).isEqualTo(CountryTestData.ITALY_DTO.getCountryName());
    assertThat(italy.getCountryCode()).isEqualTo(CountryTestData.ITALY_DTO.getCountryCode());
  }
}
