package com.gersimuca.erp.feature.country;

import com.gersimuca.erp.common.MapperConfig;
import com.gersimuca.erp.model.CountryModel;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
interface CountryMapper {
  CountryDto entityToDto(final CountryEntity entity);

  List<CountryDto> entityToDto(final List<CountryEntity> entities);

  @Mapping(source = "name", target = "countryName")
  @Mapping(target = "countryId", ignore = true)
  CountryDto externalToDto(final CountryV3Dto countryV3Dto);

  List<CountryDto> externalToDto(final List<CountryV3Dto> countryV3Dtos);

  CountryEntity toEntity(CountryDto dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "lastUpdatedAt", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  void updateEntity(final CountryDto dto, @MappingTarget final CountryEntity entity);

  CountryModel toModel(CountryDto dto);

  List<CountryModel> toModel(List<CountryDto> dtos);
}
